package com.zebone.modules.mobile.order.service.impl;

import com.zebone.modules.mobile.order.dao.CnOrderDao;
import com.zebone.modules.mobile.order.service.CnOrdService;
import com.zebone.modules.mobile.order.vo.CnOrderVO;
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
