package com.zebone.modules.mobile.bd.ou.service.impl;

import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.modules.mobile.bd.ou.repository.BdOuUserRepository;
import com.zebone.modules.mobile.bd.ou.service.BdOuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BdOuUserServiceImpl implements BdOuUserService {

    @Autowired
    private BdOuUserRepository bdOuUserRepository;

    @Override
    public BdOuUser getUser(String code) {
        return bdOuUserRepository.findByCodeUser(code);
    }
}
