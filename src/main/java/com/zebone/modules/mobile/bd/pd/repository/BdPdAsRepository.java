package com.zebone.modules.mobile.bd.pd.repository;

import com.zebone.common.entity.bd.pd.BdPdAs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdPdAsRepository extends JpaRepository<BdPdAs,String>, JpaSpecificationExecutor {
}
