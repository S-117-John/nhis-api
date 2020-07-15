package com.zebone.modules.mobile.cn.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.common.entity.bd.unit.BdUnit;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;

import com.zebone.modules.mobile.bd.ord.repository.BdOrdSetDtRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdTypeVO;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.common.constant.CnOrdConstant;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

    @ApiOperation(value = "保存药品医嘱", notes = "保存药品医嘱")
    @PostMapping("save")
    public void save(String ordList){
        Gson gson = new Gson();
        List<CnOrder> cnOrders = gson.fromJson(ordList,new TypeToken<List<CnOrder>>(){}.getType());
        //查询药品
        List<String> bdPds = new ArrayList<>();
        cnOrders.forEach(a->{bdPds.add(a.getPkOrd());});
        List<BdPd> bdPdList = bdPdService.listBdPs(bdPds);
        //将药品属性赋值给医嘱属性
        cnOrders.forEach(a->{
            bdPdList.forEach(bdPd -> {
                if(a.getPkOrd().equals(bdPd.getPkPd())){
                    //设置医嘱类型编码
                    BdOrdType bdOrdType = bdOrdTypeService.getBdOrdType(bdPd.getPkOrdtype());
                    if(bdOrdType!=null){
                        a.setCodeOrdtype(bdOrdType.getCode());
                    }
                    //设置医嘱编码
                    a.setCodeOrd(bdPd.getCode());
                    //设置医嘱名称
                    a.setNameOrd(bdPd.getName());
                    //规格
                    a.setSpec(bdPd.getSpec());

                }
            });
        });

        cnOrdService.save(cnOrders);
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
}
