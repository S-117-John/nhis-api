package com.zebone.modules.mobile.bd.supply.controller;


import com.zebone.common.entity.bd.supply.BdSupply;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.supply.repositoory.BdSupplyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "医嘱用法", tags = "医嘱用法")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_BD_SUPPLY)
public class BdSupplyController {

    @Autowired
    private BdSupplyRepository bdSupplyRepository;

    @GetMapping("")
    @ApiOperation(value = "查询医嘱用法信息", notes = "获取全部医嘱用法")
    public R<List<BdSupply>> listSupply(){

        List<BdSupply> list = bdSupplyRepository.findAll();
        return R.data(list);
    }
}
