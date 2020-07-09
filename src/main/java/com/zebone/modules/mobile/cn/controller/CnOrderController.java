package com.zebone.modules.mobile.cn.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;

import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @ApiOperation(value = "查询患者医嘱", notes = "传入pkPv")
    @GetMapping("")
    public R<List<CnOrderVO>> list(String pkPv){
        List<CnOrderVO> list = cnOrdService.listPatientOrder(pkPv);
        List<BdOrdType> bdOrdTypeList = bdOrdTypeService.listBdOrdType();
        list.forEach(a->{
            bdOrdTypeList.forEach(b->{
                if(a.getCodeOrdtype().equals(b.getCode())){
                    a.setBdOrdType(b);
                }
            });
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

                }
            });
        });

        cnOrdService.save(cnOrders);
    }
}
