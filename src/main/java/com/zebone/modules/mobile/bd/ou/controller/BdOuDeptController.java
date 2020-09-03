package com.zebone.modules.mobile.bd.ou.controller;

import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.modules.mobile.bd.ou.service.BdOuDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "科室信息", tags = "科室信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_BD_OU_DEPT)
public class BdOuDeptController {

    @Autowired
    private BdOuDeptService bdOuDeptService;

    @ApiOperation(value = "获取全部科室", notes = "获取全部科室")
    @GetMapping("")
    public List<BdOuDept> findAll(){
        return bdOuDeptService.findAll();
    }
}
