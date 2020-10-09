package com.zebone.modules.mobile.cn.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.supply.BdSupply;
import com.zebone.common.entity.bd.term.BdTermFreq;
import com.zebone.common.entity.bd.unit.BdUnit;
import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.core.gson.DoubleAdapter;
import com.zebone.core.gson.GsonUtil;
import com.zebone.core.gson.IntTypeAdapter;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.ord.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdTypeVO;
import com.zebone.modules.mobile.bd.ou.service.BdOuUserService;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import com.zebone.modules.mobile.bd.supply.service.BdSupplyService;
import com.zebone.modules.mobile.bd.term.service.BdTermFreqService;
import com.zebone.modules.mobile.cn.model.CnOrderParam;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.support.SortByOrdUtil;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderParamVO;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.constant.CnOrdConstant;
import com.zebone.modules.mobile.common.listener.ResultListener;
import com.zebone.modules.mobile.common.model.Response;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Api(value = "医嘱信息", tags = "医嘱信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_ORD)
public class CnOrderController {

    @Resource(name = "cnOrdService")
    private CnOrdService cnOrdService;

    @Resource(name = "cnOrdTreatmentService")
    private CnOrdService cnOrdTreatmentService;

    @Resource(name = "cnOrdDrugServiceImpl")
    private CnOrdService cnOrdDrugServiceImpl;

    @Autowired
    private BdOrdTypeService bdOrdTypeService;

    @Autowired
    private BdPdService bdPdService;

    @Autowired
    private BdOrdSetService bdOrdSetService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BdOuUserService bdOuUserService;

    @Autowired
    private BdSupplyService bdSupplyService;

    @Autowired
    private BdTermFreqService bdTermFreqService;


    @ApiOperation(value = "查询患者医嘱", notes = "传入pkPv")
    @GetMapping("")
    public R<List<CnOrderVO>> list(String pkPv){
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(pkPv);
        if(pvEncounterVO==null){
            return R.fail("未查询到患者信息");
        }
        List<CnOrderVO> list = new ArrayList<>();
//        List<CnOrderVO> list = cnOrdService.listPatientOrder(pvEncounterVO.getPkPv());
        List<CnOrder> cnOrderList = cnOrdService.list(pvEncounterVO.getPkPv());
        cnOrderList.forEach(cnOrder -> {
            CnOrderVO cnOrderVO = new CnOrderVO();
            BeanUtils.copyProperties(cnOrder,cnOrderVO);
            cnOrderVO.setIsnow("1");
            //医嘱用法
            BdSupply bdSupply = bdSupplyService.getSupplyByCode(cnOrder.getCodeSupply());
            if(bdSupply!=null){
                cnOrderVO.setNameSupply(bdSupply.getName());
            }

            list.add(cnOrderVO);
            //频次
            BdTermFreq bdTermFreq = bdTermFreqService.findByCode(cnOrder.getCodeFreq());
            if(bdTermFreq!=null){
                cnOrderVO.setNameFreq(bdTermFreq.getName());
            }

        });
        //医嘱类型
        List<BdOrdType> bdOrdTypeList = bdOrdTypeService.listBdOrdType();
        list.forEach(a->{
            bdOrdTypeList.forEach(b->{
            	if(null!=a.getCodeOrdtype()&& !"".equals(a.getCodeOrdtype())){
	                if(a.getCodeOrdtype().equals(b.getCode())){
	                    a.setBdOrdType(b);
	                    a.setBdOrdTypeName(b.getName());
	                    a.setIsnow("1");
	                }
            	}
            });
            a.setKey(a.getPkCnord());

        });



        if(list != null && list.size()>0){
           new SortByOrdUtil().ordGroup(list);
        }
        return R.data(list);
    }

    @ApiOperation(value = "保存医嘱", notes = "保存医嘱")
    @PostMapping("save")
    public void save(String ordList){
        CnOrderParamVO cnOrderParamVO = GsonUtil.gson.fromJson(ordList,new TypeToken<CnOrderParamVO>(){}.getType());
        if(cnOrderParamVO == null ){
            return;
        }
        String code = cnOrderParamVO.getCode();
        String codeIp = cnOrderParamVO.getCodeIp();
        String saveType = cnOrderParamVO.getSaveType();
        //科室编码
        String codeDept = cnOrderParamVO.getCodeDept();
        List<CnOrder> cnOrders = cnOrderParamVO.getCnOrdList();
        //查询药品
        List<String> bdPds = new ArrayList<>();
        cnOrders.forEach(a->{bdPds.add(a.getPkOrd());});
        //查询药品信息
        List<Map<String,Object>> bdPdList = bdPdService.getBdPdAndBdOrdByPkPd(bdPds);
        //查询医生信息
        BdOuUser user = bdOuUserService.getUser(code);
        //查询患者信息
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(codeIp);
        cnOrders = cnOrdService.setSaveCnOrder(cnOrders,bdPdList,pvEncounterVO,user);
        String pkDeptExe = cnOrdService.pkDeptExe(codeDept);
        cnOrders.forEach(cnOrder -> {
            cnOrder.setPkDeptExec(pkDeptExe);
            //就诊类型住院/
            cnOrder.setEuPvtype("3");
        });
        cnOrdService.saveOrdCnOrder(cnOrders,saveType,user);
    }

    @ApiOperation(value = "获取医嘱模板明细", notes = "传入医嘱模板pk")
    @GetMapping("temp/detail")
    public R<List<BdOrdSetDt>> ListOrderTemplateDetails(String pkOrdSet){
        List<BdOrdSetDt> list = bdOrdSetService.listBdOrdSetDt(pkOrdSet);
        return R.data(list);
    }

    @ApiOperation(value = "获取检查项目", notes = "获取检查项目")
    @GetMapping("ris/temp")
    public R<List<BdOrdTypeVO>> listRisTemp(){
        List<BdOrdType> list = bdOrdTypeService.listRis();
        List<BdOrdTypeVO> result = Lists.newArrayList();
        list.forEach(bdOrdType -> {
            BdOrdTypeVO bdOrdTypeVO = new BdOrdTypeVO();
            BeanUtils.copyProperties(bdOrdType,bdOrdTypeVO);
            bdOrdTypeVO.setTitle(bdOrdType.getName());
            bdOrdTypeVO.setKey(bdOrdType.getPkOrdtype());
            bdOrdTypeVO.setLeaf(true);
            result.add(bdOrdTypeVO);
        });

        return R.data(result);
    }

    @ApiOperation(value = "获取医嘱明细", notes = "传入医嘱主键")
    @GetMapping("detail")
    public R<CnOrderVO> getCnOrdDetail(String pkCnOrd){

        CnOrderVO cnOrderVO = cnOrdService.getCnOrderDetail(pkCnOrd);
        if(cnOrderVO==null){
            return R.fail("未查询到医嘱明细");
        }
        return R.data(cnOrderVO);
    }

    @ApiOperation(value = "检索医嘱", notes = "传入拼音")
    @GetMapping("search")
    public R<List<CnOrderVO>> search(String spCode){

        List<CnOrderVO> list = cnOrdService.search(spCode.toUpperCase());
        list.forEach(cnOrderVO -> {
            List<BdUnit> unitList = CnOrdConstant.listBdUnit.stream()
                    .filter(item -> item.getPkUnit().equals(cnOrderVO.getPkUnit()))
                    .collect(Collectors.toList());
            if(unitList.size()>0){
                cnOrderVO.setUnit(unitList.get(0).getName());
            }
        });
        return R.data(list);
    }
    
    @ApiOperation(value = "查询停嘱列表数据", notes = "传入医嘱主键")
    @GetMapping("queryStopOrdList")
    public R<List<CnOrderVO>> queryStopOrdList(String pkCnords){
        if(pkCnords==null){
            return R.fail("请选择医嘱！");
        }
        Integer cnt=cnOrdService.checkStopOrd(pkCnords);
        if(cnt>0){
        	return R.fail("所选医嘱不允许停嘱，请检查！");
        }
        List<CnOrderVO> list =cnOrdService.queryOrdStopListByPk(pkCnords);
        return R.data(list);
    }

    @ApiOperation(value = "删除医嘱", notes = "传入医嘱主键")
    @GetMapping("delOrder")
    public R<List<CnOrderVO>> delOrder(String pkCnords){
        if(pkCnords==null){
            return R.fail("请选择医嘱！");
        }
        Integer cnt=cnOrdService.checkSignOrd(pkCnords);
        if(cnt>0){
        	return R.fail("所选医嘱不能删除，请检查！");
        }
        Integer res=cnOrdService.delOrd(pkCnords);
        if(res>0){
        	return R.success("删除成功");
        }
		return null;
    }

    @ApiOperation(value = "保存检验申请", notes = "保存检验申请")
    @PostMapping("saveLisApplyList")
    public void saveLisApplyList(String param ) throws IllegalAccessException, InvocationTargetException{
    	Gson gson = new Gson();
        CnOrderParamVO cnOrderParamVO = GsonUtil.gson.fromJson(param,new TypeToken<CnOrderParamVO>(){}.getType());
		List<CnLabApplyVo> saveLisList =cnOrderParamVO.getLabApplyList();
		if(saveLisList.size()<=0) return;
		BdOuUser user=bdOuUserService.getUser(cnOrderParamVO.getCode());
		BdOuDept dept=bdOuUserService.getDept(cnOrderParamVO.getCodeDept());
		PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParamVO.getCodeIp());
	    cnOrdService.saveLisApplyList(user, saveLisList,pvEncounterVO,dept);
	}

	@ApiOperation(value = "保存检查申请", notes = "保存检查申请")
    @PostMapping("saveRisApplyList")
    public void saveRisApplyList(String param) throws IllegalAccessException, InvocationTargetException{
    	Gson gson = new Gson();
        CnOrderParamVO cnOrderParamVO = GsonUtil.gson.fromJson(param,new TypeToken<CnOrderParamVO>(){}.getType());
		List<CnRisApplyVo> saveRisList =cnOrderParamVO.getRisApplyList();
		if(saveRisList.size()<=0) {
            return;
        }
		BdOuUser user=bdOuUserService.getUser(cnOrderParamVO.getCode());
		BdOuDept dept=bdOuUserService.getDept(cnOrderParamVO.getCodeDept());
		PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParamVO.getCodeIp());
		cnOrdService.saveRisApplyList(user, saveRisList,pvEncounterVO,dept);  
	}

    @ApiOperation(value = "停止医嘱", notes = "停止医嘱（批量）")
    @PutMapping("stop")
	public void stop(String param){
        List<CnOrder> cnOrderList = GsonUtil.gson.fromJson(param,new TypeToken<List<CnOrder>>(){}.getType());
        cnOrdService.stop(cnOrderList);
    }

    @ApiOperation(value = "签署医嘱", notes = "签署医嘱")
    @PutMapping("sign")
    public void sign(String param){
        CnOrderParamVO cnOrderParamVO = GsonUtil.gson.fromJson(param,CnOrderParamVO.class);
        List<CnOrder> cnOrderList = cnOrderParamVO.getCnOrdList();
        List<CnOrder> uncnSignList = cnOrderList.stream().filter(cnOrder -> "0".equals(cnOrder.getFlagSign())).collect(Collectors.toList());
        cnOrdService.sign(uncnSignList);
        cnOrdTreatmentService.sign(uncnSignList);
    }

    @ApiOperation(value = "获取执行科室", notes = "当前科室业务线对应执行科室")
    @GetMapping("exeDept")
    public List<BdOuDept> getExeDept(String deptCode){
        return cnOrdService.getExeDeptList(deptCode);
    }


    @ApiOperation(value = "保存治疗医嘱", notes = "保存治疗医嘱")
    @PostMapping("saveTreatment")
    public void saveTreatment(CnOrderParam cnOrderParam){

        cnOrdTreatmentService.save(cnOrderParam, new ResultListener() {
            @Override
            public void success(Object object) {

            }

            @Override
            public void error(Object object) {

            }

            @Override
            public void exception(Object object) {

            }
        });

    }


    @ApiOperation(value = "保存药品医嘱", notes = "保存药品医嘱")
    @PostMapping("saveDrug")
    public void saveDrug(String ordList){
        CnOrderParamVO cnOrderParamVO = GsonUtil.gson.fromJson(ordList,new TypeToken<CnOrderParamVO>(){}.getType());
        if(cnOrderParamVO == null ){
            return;
        }
        //医生编码
        String userCode = cnOrderParamVO.getCode();
        //住院号
        String codeIp = cnOrderParamVO.getCodeIp();
        //科室编码
        String codeDept = cnOrderParamVO.getCodeDept();
        List<CnOrder> cnOrders = cnOrderParamVO.getCnOrdList();

        //查询医生信息
        BdOuUser user = bdOuUserService.getUser(userCode);
        //查询患者信息
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(codeIp);
        String pkDeptExe = cnOrdService.pkDeptExe(codeDept);
        cnOrders.forEach(cnOrder -> {
            cnOrder.setPkDeptExec(pkDeptExe);
            cnOrder.setPkPv(pvEncounterVO.getPkPv());
            cnOrder.setPkPi(pvEncounterVO.getPkPi());
            cnOrder.setPkEmpInput(user.getPkEmp());
            cnOrder.setNameEmpInput(user.getNameUser());
            cnOrder.setPkDept(pvEncounterVO.getPkDept());
            cnOrder.setPkDeptNs(pvEncounterVO.getPkDeptNs());
            cnOrder.setCreator(user.getPkEmp());
        });
        cnOrdDrugServiceImpl.save(cnOrders);
    }


    @ApiOperation(value = "保存签署药品医嘱", notes = "保存签署药品医嘱")
    @PostMapping("saveAndSignDrug")
    public Response<String> saveAndSignDrug(@RequestBody CnOrderParam cnOrderParam){
        Response<String> response = new Response<>();
        cnOrdDrugServiceImpl.saveAndSign(cnOrderParam, new ResultListener() {
            @Override
            public void success(Object object) {
                response.setCode(200);
                response.setMessage("签署成功");
            }

            @Override
            public void error(Object object) {
                response.setCode(500);
                response.setMessage((String)object);
            }

            @Override
            public void exception(Object object) {
                response.setCode(500);
                response.setMessage((String)object);
            }
        });
        return response;
    }
    
}
