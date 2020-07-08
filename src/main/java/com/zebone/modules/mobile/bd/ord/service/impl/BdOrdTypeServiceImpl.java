package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdOrdTypeServiceImpl implements BdOrdTypeService {

    @Autowired
    private BdOrdTypeRepository bdOrdTypeRepository;

    @Override
    public List<BdOrdType> listBdOrdType() {
        return bdOrdTypeRepository.findAll();
    }
}
