package com.zebone.modules.mobile.bd.ord.service;

import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;

import java.util.List;

public interface OrdPdService {

    /**
     * 检索非药品
     * @param spCode
     * @return
     */
    List<OrdPdVO> listOrd(String spCode);

    /**
     * 检索药品
     * @param spCode
     * @return
     */
    List<OrdPdVO> listPd(String spCode);
}
