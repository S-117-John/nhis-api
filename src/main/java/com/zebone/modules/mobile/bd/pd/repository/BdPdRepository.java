package com.zebone.modules.mobile.bd.pd.repository;

import com.zebone.common.entity.bd.pd.BdPd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdPdRepository extends JpaRepository<BdPd,String>, JpaSpecificationExecutor {
}
