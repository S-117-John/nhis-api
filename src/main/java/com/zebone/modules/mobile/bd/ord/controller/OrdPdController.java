package com.zebone.modules.mobile.bd.ord.controller;

import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.ord.service.OrdPdService;
import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(value = "医嘱检索", tags = "医嘱检索")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_ORD_PD)
public class OrdPdController {

    @Autowired
    private OrdPdService ordPdService;

    @GetMapping("list")
    @ApiOperation(value = "医嘱检索", notes = "输入拼音码")
    public R<List<OrdPdVO>> listOrd(String spCode){
        if(StringUtils.isEmpty(spCode)){
           return R.fail("请输入拼音码");
        }
        List<OrdPdVO> list = ordPdService.listOrd(spCode.toUpperCase());
        List<OrdPdVO> listPd = ordPdService.listPd(spCode.toUpperCase());
        list.addAll(listPd);
        return R.data(list);
    }

    @SuppressWarnings("rawtypes")
	@GetMapping("getLisOrRisDetail")
    @ApiOperation(value = "获取检查检验详情和执行科室", notes = "医嘱项目主键和02或者03")
    public R<Map<String, List>> getLisOrRisDetail(String pkOrd,String LisOrRis){
        if(StringUtils.isEmpty(pkOrd)){
           return R.fail("请医嘱项目主键");
        }
        return R.data(ordPdService.getLisOrRisDetail(pkOrd,LisOrRis));
    }
}
