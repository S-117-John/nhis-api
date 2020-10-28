package com.zebone.modules.mobile.bd.ord.repository;

import com.zebone.common.entity.bd.ord.BdOrdLab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BdOrdLisRepository extends JpaRepository<BdOrdLab,String> {

    List<BdOrdLab> findByPkOrd(String pkOrd);
}
