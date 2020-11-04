package com.zebone.modules.mobile.patient.repository;

import com.zebone.common.entity.pv.PvEncounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PvEncounterRepository extends JpaRepository<PvEncounter,String> {

    List<PvEncounter> findByPkPi(String pkPi);

    PvEncounter findByCodePv(String codePv);
}
