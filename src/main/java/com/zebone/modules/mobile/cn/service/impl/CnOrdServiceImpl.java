package com.zebone.modules.mobile.cn.service.impl;


import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CnOrdServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return cnOrderDao.listPatientOrder(pkPv);
    }

    @Override
    public void save(List<CnOrder> cnOrders) {
        cnOrderRepository.saveAll(cnOrders);
    }
}
