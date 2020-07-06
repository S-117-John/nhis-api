package com.zebone.modules.mobile.bd.service.impl;

import com.zebone.modules.mobile.bd.dao.BdOrdSetDao;
import com.zebone.modules.mobile.bd.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.vo.BdOrdSetVO;
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
