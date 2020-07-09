package com.zebone.modules.mobile.bd.pd.service.impl;

import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.modules.mobile.bd.pd.dao.BdPdDao;
import com.zebone.modules.mobile.bd.pd.repository.BdPdRepository;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BdPdServiceImpl implements BdPdService {

    @Autowired
    private BdPdDao bdPdDao;

    @Autowired
    private BdPdRepository bdPdRepository;

    @Override
    public BdPdVO getBdPd(String pkPd) {
        Optional<BdPd> optionalBdPd = bdPdRepository.findById(pkPd);
        if(!optionalBdPd.isPresent()){
            return null;
        }
        BdPdVO bdPdVO = new BdPdVO();
        BdPd bdPd = optionalBdPd.get();
        BeanUtils.copyProperties(bdPd,bdPdVO);

        return bdPdVO;
    }

    @Override
    public List<BdPd> listBdPs(List<String> ids) {
        List<BdPd> bdPdList = bdPdRepository.findAllById(ids);

        return bdPdList;
    }
}
