package com.zebone.modules.mobile.cn.repository;

import com.zebone.common.entity.cn.CnOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CnOrderRepository extends JpaRepository<CnOrder,String> {

}
