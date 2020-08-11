package com.zebone.modules.mobile.bd.dept.repository;

import com.zebone.common.entity.bd.dept.BdDeptBus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdDeptBusRepository extends JpaRepository<BdDeptBus,String>, JpaSpecificationExecutor<BdDeptBus> {
}
