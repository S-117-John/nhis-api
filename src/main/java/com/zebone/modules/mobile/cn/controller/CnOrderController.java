package com.zebone.modules.mobile.cn.controller;

import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;

import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "医嘱信息", tags = "医嘱信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_ORD)
public class CnOrderController {

    @Autowired
    private CnOrdService cnOrdService;

    @ApiOperation(value = "查询患者医嘱", notes = "传入pkPv")
    @GetMapping("")
    public R<List<CnOrderVO>> list(String pkPv){
        List<CnOrderVO> list = cnOrdService.listPatientOrder(pkPv);
        return R.data(list);
    }
}
