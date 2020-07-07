package com.zebone.modules.mobile.bd.service.impl;

import com.zebone.modules.mobile.bd.dao.OrdPdDao;
import com.zebone.modules.mobile.bd.service.OrdPdService;
import com.zebone.modules.mobile.bd.vo.OrdPdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdPdServiceImpl implements OrdPdService {

    @Autowired
    private OrdPdDao ordPdDao;

    @Override
    public List<OrdPdVO> listOrd(String spCode) {
        return ordPdDao.listOrd(spCode);
    }

    @Override
    public List<OrdPdVO> listPd(String spCode) {
        return ordPdDao.listPd(spCode);
    }
}
