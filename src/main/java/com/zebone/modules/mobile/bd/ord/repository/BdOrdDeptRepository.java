package com.zebone.modules.mobile.bd.ord.repository;

import com.zebone.common.entity.bd.ord.BdOrdDept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BdOrdDeptRepository extends JpaRepository<BdOrdDept,String> {

    List<BdOrdDept> findByPkOrd(String pkOrd);
}
