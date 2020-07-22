package com.zebone.modules.mobile.bd.pd.controller;

import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@Api(value = "药品信息", tags = "药品信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_DRUG)
public class BdPdController {

    @Autowired
    private BdPdService bdPdService;

    @GetMapping("")
    @ApiOperation(value = "查询药品信息", notes = "输入药品主键")
    public R<BdPdVO> getBdPd(String pkPd){
        BdPdVO bdPdVO = bdPdService.getBdPd(pkPd);
        return R.data(bdPdVO);
    }

    @GetMapping("list")
    @ApiOperation(value = "批量查询药品信息", notes = "药品主键集合")
    public R<List<BdPdVO>> listBdPd(String ids){

        List<BdPdVO> bdPdList = bdPdService.listBdPs(Arrays.asList(ids.split(",")));
        return R.data(bdPdList);
    }

}
