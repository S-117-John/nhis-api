package com.zebone.modules.mobile.bd.dept.repository;

import com.zebone.common.entity.bd.dept.BdDeptBu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BdDeptBuRepository extends JpaRepository<BdDeptBu,String>, JpaSpecificationExecutor<BdDeptBu> {
}
