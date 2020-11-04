package com.zebone.modules.mobile.patient.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;

public interface PatientService {


    PvEncounterVO getPatientInfo(String code);
}
