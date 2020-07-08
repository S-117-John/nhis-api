package com.zebone.modules.mobile.patient.service.impl;

import com.zebone.common.entity.pi.PiCate;
import com.zebone.common.entity.pi.PiMaster;
import com.zebone.common.entity.pv.PvDiag;
import com.zebone.common.entity.pv.PvEncounter;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.patient.dao.PvEncounterDao;
import com.zebone.modules.mobile.patient.repository.PiCateRepository;
import com.zebone.modules.mobile.patient.repository.PiMasterRepository;
import com.zebone.modules.mobile.patient.repository.PvDiagRepository;
import com.zebone.modules.mobile.patient.repository.PvEncounterRepository;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PiCateVO;
import com.zebone.modules.mobile.patient.vo.PiMasterVO;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PvEncounterDao pvEncounterDao;

    @Autowired
    private PiCateRepository piCateRepository;

    @Autowired
    private PvEncounterRepository pvEncounterRepository;

    @Autowired
    private PiMasterRepository piMasterRepository;

    @Autowired
    private PvDiagRepository pvDiagRepository;

    @Override
    public PvEncounterVO getPatientInfo(String pkPv) {

        //获取就诊信息
        Optional<PvEncounter> pvEncounter = pvEncounterRepository.findById(pkPv);
        if(!pvEncounter.isPresent()){
            return null;
        }
        PvEncounterVO pvEncounterVO = new PvEncounterVO();
        BeanUtils.copyProperties(pvEncounter.get(),pvEncounterVO);

        //获取患者基本信息
        PiMaster piMaster = piMasterRepository.getOne(pvEncounterVO.getPkPi());
        PiMasterVO piMasterVO = new PiMasterVO();
        BeanUtils.copyProperties(piMaster,piMasterVO);

        pvEncounterVO.setPiMaster(piMasterVO);

        //获取患者类型
        PiCate piCate = piCateRepository.getOne(piMasterVO.getPkPicate());
        PiCateVO piCateVO = new PiCateVO();
        BeanUtils.copyProperties(piCate,piCateVO);
        piMasterVO.setPiCate(piCateVO);

        //获取诊断记录
        List<PvDiag> pvDiagList = pvDiagRepository.findByPkPvOrderByDateDiagDesc(pkPv);
        if(pvDiagList.size()>0){
            //筛选主诊断记录
            List<PvDiag> diagList = pvDiagList.stream().filter(a->"1".equals(a.getFlagMaj())).collect(Collectors.toList());
            if(diagList.size()>0){
                pvEncounterVO.setDiagName(diagList.get(0).getNameDiag());
            }else{
                pvEncounterVO.setDiagName(pvDiagList.get(0).getNameDiag());
            }
        }

        return pvEncounterVO;
    }
}
