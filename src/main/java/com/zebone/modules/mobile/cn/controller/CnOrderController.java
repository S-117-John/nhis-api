package com.zebone.modules.mobile.cn.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.unit.BdUnit;
import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.ord.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdTypeVO;
import com.zebone.modules.mobile.bd.ou.service.BdOuUserService;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderParamVO;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.constant.CnOrdConstant;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Api(value = "医嘱信息", tags = "医嘱信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_ORD)
public class CnOrderController {

    @Autowired
    private CnOrdService cnOrdService;

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


    @ApiOperation(value = "查询患者医嘱", notes = "传入pkPv")
    @GetMapping("")
    public R<List<CnOrderVO>> list(String pkPv){
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(pkPv);
        if(pvEncounterVO==null){
            return R.fail("未查询到患者信息");
        }
        List<CnOrderVO> list = cnOrdService.listPatientOrder(pvEncounterVO.getPkPv());
        List<BdOrdType> bdOrdTypeList = bdOrdTypeService.listBdOrdType();
        list.forEach(a->{
            bdOrdTypeList.forEach(b->{
                if(a.getCodeOrdtype().equals(b.getCode())){
                    a.setBdOrdType(b);
                    a.setBdOrdTypeName(b.getName());
                }
            });
            a.setKey(a.getPkCnord());

        });
        return R.data(list);
    }

    @ApiOperation(value = "保存医嘱", notes = "保存医嘱")
    @PostMapping("save")
    public void save(String ordList){
        Gson gson = new Gson();
        CnOrderParamVO cnOrderParamVO = gson.fromJson(ordList,new TypeToken<CnOrderParamVO>(){}.getType());
        if(cnOrderParamVO == null ){
            return;
        }
        String code = cnOrderParamVO.getCode();
        String codeIp = cnOrderParamVO.getCodeIp();
        List<CnOrder> cnOrders = cnOrderParamVO.getCnOrdList();
        //查询药品
        List<String> bdPds = new ArrayList<>();
        cnOrders.forEach(a->{bdPds.add(a.getPkOrd());});
        Date d = new Date();
        //查询药品信息
        List<Map<String,Object>> bdPdList = bdPdService.getBdPdAndBdOrdByPkPd(bdPds);
        //查询医生信息
        BdOuUser user = bdOuUserService.getUser(code);
        //查询患者信息
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(codeIp);
        //将药品属性赋值给医嘱属性
        int i = 0;
        Integer ordsn = 0;
        for(CnOrder cn :cnOrders){
            i++;
            String pkCnord = StringUtil.isNullOrEmpty(cn.getPkCnord())? UUID.randomUUID().toString():cn.getPkCnord();
            cn.setPkCnord(pkCnord);
            cn.setEuStatusOrd("0");
            if(i ==1){
                ordsn = cnOrdService.getSerialNo("CN_ORDER", "ORDSN", 1);
                cn.setOrdsn(ordsn);
                cn.setOrdsnParent(ordsn);
            }else{
                cn.setOrdsn(cnOrdService.getSerialNo("CN_ORDER", "ORDSN", 1));
                cn.setOrdsnParent(ordsn);
            }
            cn.setPkPv(pvEncounterVO.getPkPv());
            cn.setPkPi(pvEncounterVO.getPkPi());
            cn.setTs(d);
            cn.setDateEnter(d);
            cn.setOrdsnChk(cn.getOrdsn());
            cn.setPkEmpInput(user.getPkEmp());
            cn.setNameEmpInput(user.getNameEmp());
            cn.setPkDept(pvEncounterVO.getPkDept());
            cn.setPkDeptNs(pvEncounterVO.getPkDeptNs());
            cn.setPkEmpOrd(user.getPkEmp());
            cn.setNameEmpOrd(user.getNameEmp());
            cn.setCreator(user.getPkEmp());
            cn.setCreateTime(d);
            cn.setDelFlag("0");
            cn.setTs(d);
            for(Map<String,Object> m : bdPdList){
                if(cn.getPkOrd().equals(m.get("PK_PD").toString())){
                    cn.setCodeOrdtype(m.get("CODE_ORDTYPE").toString());
                    cn.setCodeOrd(m.get("CODE").toString());
                    cn.setNameOrd(m.get("NAME").toString());
                    cn.setSpec(m.get("SPEC").toString());
                    cn.setDosage(Double.valueOf(m.get("QUAN_MIN").toString()));
                    cn.setPkUnitDos(m.get("PK_UNIT_MIN").toString());
                    cn.setQuan(Double.valueOf(m.get("PACK_SIZE").toString()));
                    cn.setPkUnit(m.get("PK_UNIT").toString());
                    if(Double.valueOf(m.get("PACK_SIZE").toString())!=0 && "0".equals(cn.getEuAlways())&& !StringUtil.isNullOrEmpty(m.get("PK_PD").toString())){
                        double execTimes = (cn.getFirstNum() == null || cn.getFirstNum()==0) ? (Integer.getInteger(m.get("CNT").toString()) == 0 ? 1 : Integer.getInteger(m.get("CNT").toString())) : cn.getFirstNum();
                        if("0".equals(m.get("EU_MUPUTYPE").toString())){//按次取整
                            cn.setQuanCg(Math.floor(Double.valueOf(m.get("PACK_SIZE").toString())/(Double.valueOf(m.get("PACK_SIZE").toString()) > 1 ? 1 : Double.valueOf(m.get("PACK_SIZE").toString())))*execTimes);
                        }else{ //按批取整
                            cn.setQuanCg(Math.floor(Double.valueOf(m.get("PACK_SIZE").toString())/(Double.valueOf(m.get("PACK_SIZE").toString()) > 1 ? 1 : Double.valueOf(m.get("PACK_SIZE").toString())))*execTimes);
                        }
                    }
                    cn.setPkUnitCg(m.get("PK_UNIT").toString());
                    cn.setPackSize(Double.valueOf(m.get("PACK_SIZE").toString()));
                    cn.setPriceCg(Double.valueOf(m.get("PRICE").toString()));
                    if(cn.getFirstNum() != null &&cn.getFirstNum()>0){
                        cn.setFlagFirst("1");
                    }else{
                        cn.setFlagFirst("0");
                    }
                    if(!StringUtil.isNullOrEmpty(m.get("PK_PD").toString())){
                        cn.setFlagDurg("1");
                    }else {
                        cn.setFlagDurg("0");
                    }
                    break;
                }
            }
        }
        if(cnOrders!=null && cnOrders.size()>0){
            cnOrdService.save(cnOrders);
            cnOrdService.saveOrdAnti(cnOrders,user.getPkOrg());
        }


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
    @ApiOperation(value = "签署医嘱", notes = "传入医嘱主键")
    @GetMapping("signOrder")
    public R<List<CnOrderVO>> signOrder(String pkCnords,String doctorCode){
        if(pkCnords==null){
            return R.fail("请选择医嘱！");
        }
        if(null==doctorCode || doctorCode.equals("")){
        	 return R.fail("当前操作医生为空！");
        }
        Integer cnt=cnOrdService.checkSignOrd(pkCnords);
        if(cnt>0){
        	return R.fail("所选医嘱不能签署，请检查！");
        }
        BdOuUser user=bdOuUserService.getUser(doctorCode);
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("pkCnord", pkCnords);
        map.put("dateStop", new Date());
        map.put("pkEmpStop", user.getPkEmp());
        map.put("nameEmpStop", user.getNameUser());
        Integer res=cnOrdService.signOrd(map);
        if(res>0){
        	return R.success("签署成功");
        }
        return R.fail("签署失败！");
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
        CnOrderParamVO cnOrderParamVO = gson.fromJson(param,new TypeToken<CnOrderParamVO>(){}.getType());
		List<CnLabApplyVo> saveLisList =cnOrderParamVO.getLabApplyList();
		if(saveLisList.size()<=0) return;
		BdOuUser user=bdOuUserService.getUser(cnOrderParamVO.getCode());
		PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParamVO.getCodeIp());
	    cnOrdService.saveLisApplyList(user, saveLisList,pvEncounterVO);
	}
    @ApiOperation(value = "保存检查申请", notes = "保存检查申请")
    @PostMapping("saveRisApplyList")
    public void saveRisApplyList(String param) throws IllegalAccessException, InvocationTargetException{
    	Gson gson = new Gson();
        CnOrderParamVO cnOrderParamVO = gson.fromJson(param,new TypeToken<CnOrderParamVO>(){}.getType());
		List<CnRisApplyVo> saveRisList =cnOrderParamVO.getRisApplyList();
		if(saveRisList.size()<=0) return;
		BdOuUser user=bdOuUserService.getUser(cnOrderParamVO.getCode());
		PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParamVO.getCodeIp());
		cnOrdService.saveRisApplyList(user, saveRisList,pvEncounterVO);  
	}
    
}
