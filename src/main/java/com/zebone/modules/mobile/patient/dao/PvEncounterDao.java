package com.zebone.modules.mobile.patient.dao;


import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PvEncounterDao {

    PvEncounterVO getPvEncounter(String pkPv);
}