package com.zebone.modules.mobile.bd.pd.service.impl;

import com.zebone.modules.mobile.bd.pd.dao.BdPdDao;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BdPdServiceImpl implements BdPdService {

    @Autowired
    private BdPdDao bdPdDao;

    @Override
    public BdPdVO getBdPd(String pkPd) {
        return bdPdDao.getBdPd(pkPd);
    }
}
