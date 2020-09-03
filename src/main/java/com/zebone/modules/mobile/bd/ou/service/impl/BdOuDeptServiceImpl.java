package com.zebone.modules.mobile.bd.ou.service.impl;

import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
import com.zebone.modules.mobile.bd.ou.service.BdOuDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdOuDeptServiceImpl implements BdOuDeptService {

    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;

    @Override
    public List<BdOuDept> findAll() {
        return bdOuDeptRepository.findAll();
    }
}
