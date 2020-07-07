package com.zebone.modules.mobile.patient.controller;

import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "病人信息", tags = "病人信息")
@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_PATIENT)
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "查询患者信息", notes = "传入pkPv")
    @GetMapping("")
    private R<PvEncounterVO> getPatientInfo(String pkPv){

        if(StringUtils.isEmpty(pkPv)){
            return R.fail("患者主键为空");
        }
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(pkPv);
        if(pvEncounterVO==null){
            return R.fail("患者信息不存在");
        }
        return R.data(pvEncounterVO);
    }

}
