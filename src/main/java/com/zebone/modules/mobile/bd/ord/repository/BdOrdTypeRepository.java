package com.zebone.modules.mobile.bd.ord.repository;

import com.zebone.common.entity.bd.ord.BdOrdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdOrdTypeRepository extends JpaRepository<BdOrdType,String>, JpaSpecificationExecutor {
}
