package com.zebone.modules.mobile.bd.ord.repository;

import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdOrdSetDtRepository extends JpaRepository<BdOrdSetDt,String>, JpaSpecificationExecutor {


}
