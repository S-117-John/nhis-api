package com.zebone.modules.mobile.patient.repository;

import com.zebone.common.entity.pv.PvDiag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PvDiagRepository extends JpaRepository<PvDiag,String> {

    List<PvDiag> findByPkPvOrderByDateDiagDesc(String pkPv);
}
