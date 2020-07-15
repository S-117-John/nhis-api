package com.zebone.modules.mobile.patient.repository;

import com.zebone.common.entity.pi.PiMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PiMasterRepository extends JpaRepository<PiMaster,String> {

    List<PiMaster> findByCodeIp(String code);
}
