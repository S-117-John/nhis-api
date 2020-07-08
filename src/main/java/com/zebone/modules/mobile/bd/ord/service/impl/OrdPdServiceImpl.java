package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.modules.mobile.bd.ord.dao.OrdPdDao;
import com.zebone.modules.mobile.bd.ord.service.OrdPdService;
import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;
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
