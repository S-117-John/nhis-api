package com.zebone.modules.mobile.bd.supply.service.impl;

import com.zebone.common.entity.bd.supply.BdSupply;
import com.zebone.modules.mobile.bd.supply.repositoory.BdSupplyRepository;
import com.zebone.modules.mobile.bd.supply.service.BdSupplyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BdSupplyServiceImpl implements BdSupplyService {

    @Autowired
    private BdSupplyRepository bdSupplyRepository;

    @Override
    public List<BdSupply> listSupply() {
        return bdSupplyRepository.findAll();
    }
}
