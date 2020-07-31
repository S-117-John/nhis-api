package com.zebone.modules.mobile.cn.repository;

import com.zebone.common.entity.cn.CnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CnOrderRepository extends JpaRepository<CnOrder,String>, JpaSpecificationExecutor {

}
