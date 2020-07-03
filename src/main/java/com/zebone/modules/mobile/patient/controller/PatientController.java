package com.zebone.modules.mobile.patient.controller;

import com.zebone.core.launch.constant.AppConstant;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppConstant.APPLICATION_MOBILE_PATIENT)
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    private R<PvEncounterVO> getPatientInfo(String pkPv){

        if(StringUtils.isEmpty(pkPv)){
            return R.fail("患者主键为空");
        }

        return R.data(patientService.getPatientInfo(pkPv));
    }

}
