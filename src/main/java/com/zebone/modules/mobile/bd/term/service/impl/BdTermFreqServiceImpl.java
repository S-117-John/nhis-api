package com.zebone.modules.mobile.bd.term.service.impl;

import com.zebone.common.entity.bd.term.BdTermFreq;
import com.zebone.modules.mobile.bd.term.repository.BdTermFreqRepository;
import com.zebone.modules.mobile.bd.term.service.BdTermFreqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BdTermFreqServiceImpl implements BdTermFreqService {

    @Autowired
    private BdTermFreqRepository bdTermFreqRepository;

    @Override
    public List<BdTermFreq> list() {
        return bdTermFreqRepository.findAll();
    }

    @Override
    public BdTermFreq findByCode(String code) {
        BdTermFreq bdTermFreq = null;
        try{
            bdTermFreq = bdTermFreqRepository.findByCode(code);
        }catch (Exception e){

        }

        return bdTermFreq;
    }
}
