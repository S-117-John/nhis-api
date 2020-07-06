package com.zebone.modules.mobile.bd.controller;

import com.google.common.collect.Lists;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.vo.BdOrdSetVO;
import com.zebone.modules.mobile.bd.wrapper.BdOrdSetWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "医嘱模板", tags = "医嘱模板")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_ORD_SET)
public class BdOrdSetController {

    @Autowired
    private BdOrdSetService bdOrdSetService;

    @ApiOperation(value = "获取操作员信息", notes = "传入id")
    @GetMapping("emp")
    public R<List<BdOrdSetVO>> listEmpOrd(String pkEmp){
        List<BdOrdSetVO> list = bdOrdSetService.listEmpBdOrdSet(pkEmp);
        return R.data(list);
    }

}
