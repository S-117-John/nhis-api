package com.zebone.modules.mobile.bd.supply.repositoory;

import com.zebone.common.entity.bd.supply.BdSupply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BdSupplyRepository extends JpaRepository<BdSupply,String> {

    BdSupply findByCode(String code);
}
