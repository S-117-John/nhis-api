package com.zebone.modules.mobile.bd.repository;

import com.zebone.common.entity.bd.BdDefdoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BdDefdocRepository extends JpaRepository<BdDefdoc,String> {

    List<BdDefdoc> findByCodeAndCodeDefdoclist(String code,String codeDefDocList);
    List<BdDefdoc> findByCodeDefdoclist(String code);
}
