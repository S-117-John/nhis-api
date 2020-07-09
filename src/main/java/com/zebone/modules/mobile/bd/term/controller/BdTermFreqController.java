package com.zebone.modules.mobile.bd.term.controller;

import com.zebone.common.entity.bd.term.BdTermFreq;
import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.term.service.BdTermFreqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "医嘱频次", tags = "医嘱频次")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_BD_TERM)
public class BdTermFreqController {

    @Autowired
    private BdTermFreqService bdTermFreqService;

    @GetMapping("freq")
    @ApiOperation(value = "查询医嘱频次列表", notes = "查询医嘱频次列表")
    public R<List<BdTermFreq>> listFreq(){
        List<BdTermFreq> list = bdTermFreqService.list();
        return R.data(list);
    }
}
