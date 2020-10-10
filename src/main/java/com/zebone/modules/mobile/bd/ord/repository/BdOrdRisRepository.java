package com.zebone.modules.mobile.bd.ord.repository;

import com.zebone.common.entity.bd.ord.BdOrdRis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BdOrdRisRepository extends JpaRepository<BdOrdRis,String> {

    List<BdOrdRis> findByPkOrd(String pkOrd);
}
