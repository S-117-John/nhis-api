package com.zebone.modules.mobile.cn.service.impl;


import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CnOrdServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    /**
     * 查询已开立医嘱
     * @param pkPv
     * @return
     */
    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return cnOrderDao.listPatientOrder(pkPv);
    }

    /**
     * 保存医嘱
     * @param cnOrders
     */
    @Override
    public void save(List<CnOrder> cnOrders) {
        cnOrders.forEach(cnOrder -> {
            cnOrder.setCreateTime(new Date());
            cnOrder.setFlagErase("0");
        });
        cnOrderRepository.saveAll(cnOrders);
    }

    /**
     * 医嘱详情
     * @param pkCnord
     * @return
     */
    @Override
    public CnOrderVO getCnOrderDetail(String pkCnord) {
        Optional<CnOrder> optional = cnOrderRepository.findById(pkCnord);
        if(!optional.isPresent()){
            return null;
        }
        CnOrder cnOrder = optional.get();
        CnOrderVO cnOrderVO = new CnOrderVO();
        BeanUtils.copyProperties(cnOrder,cnOrderVO);

        return cnOrderVO;
    }

    /**
     * 检索医嘱
     * @param spCode
     * @return
     */
    @Override
    public List<CnOrderVO> search(String spCode) {
        //1、查询药品

        //2、查询非药品
        return null;
    }
}
