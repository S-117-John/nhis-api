package com.zebone.modules.mobile.patient.service.impl;

import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.patient.dao.PvEncounterDao;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PvEncounterDao pvEncounterDao;

    @Override
    public PvEncounterVO getPatientInfo(String pkPv) {
        return pvEncounterDao.getPvEncounter(pkPv);
    }
}
