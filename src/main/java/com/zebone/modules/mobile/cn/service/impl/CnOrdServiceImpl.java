package com.zebone.modules.mobile.cn.service.impl;


import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CnOrdServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return cnOrderDao.listPatientOrder(pkPv);
    }
}
