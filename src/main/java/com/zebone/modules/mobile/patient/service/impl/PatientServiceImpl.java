package com.zebone.modules.mobile.patient.service.impl;

import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.pi.PiCate;
import com.zebone.common.entity.pi.PiMaster;
import com.zebone.common.entity.pv.PvDiag;
import com.zebone.common.entity.pv.PvEncounter;
import com.zebone.core.tool.api.R;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {



    @Autowired
    private PiCateRepository piCateRepository;

    @Autowired
    private PvEncounterRepository pvEncounterRepository;

    @Autowired
    private PiMasterRepository piMasterRepository;

    @Autowired
    private PvDiagRepository pvDiagRepository;

    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;



    @Override
    public PvEncounterVO getPatientInfo(String code) {

        PvEncounter pvEncounter = pvEncounterRepository.findByCodePv(code);

        //获取患者基本信息
        Optional<PiMaster> optionalPiMaster = piMasterRepository.findById(pvEncounter.getPkPi());
        PiMaster piMaster = optionalPiMaster.get();
        if(piMaster!=null){
            PiMasterVO piMasterVO = new PiMasterVO();
            BeanUtils.copyProperties(piMaster,piMasterVO);

            //获取就诊信息
            PvEncounterVO pvEncounterVO = new PvEncounterVO();
            BeanUtils.copyProperties(pvEncounter,pvEncounterVO);

            pvEncounterVO.setPiMaster(piMasterVO);

            //获取患者类型
            PiCate piCate = piCateRepository.getOne(piMasterVO.getPkPicate());
            PiCateVO piCateVO = new PiCateVO();
            BeanUtils.copyProperties(piCate,piCateVO);
            piMasterVO.setPiCate(piCateVO);

            //获取诊断记录
            List<PvDiag> pvDiagList = pvDiagRepository.findByPkPvOrderByDateDiagDesc(pvEncounterVO.getPkPv());
            if(pvDiagList.size()>0){
                //筛选主诊断记录
                List<PvDiag> diagList = pvDiagList.stream().filter(a->"1".equals(a.getFlagMaj())).collect(Collectors.toList());
                if(diagList.size()>0){
                    pvEncounterVO.setDiagName(diagList.get(0).getNameDiag());
                }else{
                    pvEncounterVO.setDiagName(pvDiagList.get(0).getNameDiag());
                }
            }

            if(!StringUtils.isEmpty(pvEncounterVO.getPkDept())){
                Optional<BdOuDept> optional = bdOuDeptRepository.findById(pvEncounterVO.getPkDept());
                if(optional.isPresent()){
                    pvEncounterVO.setDeptName(optional.get().getNameDept());
                }
            }


            return pvEncounterVO;
        }

        return null;
    }
}
