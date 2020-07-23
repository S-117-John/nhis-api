package com.zebone.modules.mobile.bd.unit.service.impl;

import com.zebone.common.entity.bd.unit.BdUnit;
import com.zebone.modules.mobile.bd.unit.repository.BdUnitRepository;
import com.zebone.modules.mobile.bd.unit.service.BdUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdUnitServiceImpl implements BdUnitService {

    @Autowired
    private BdUnitRepository bdUnitRepository;

    @Override
    public List<BdUnit> listBdUnit() {
        return bdUnitRepository.findAll();
    }
}
