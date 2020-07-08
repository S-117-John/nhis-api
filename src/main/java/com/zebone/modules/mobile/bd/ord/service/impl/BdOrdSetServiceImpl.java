package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.modules.mobile.bd.ord.dao.BdOrdSetDao;
import com.zebone.modules.mobile.bd.ord.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdOrdSetServiceImpl implements BdOrdSetService {

    @Autowired
    private BdOrdSetDao bdOrdSetDao;


    @Override
    public List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp) {
        return bdOrdSetDao.listEmpBdOrdSet(pkEmp);
    }
}
