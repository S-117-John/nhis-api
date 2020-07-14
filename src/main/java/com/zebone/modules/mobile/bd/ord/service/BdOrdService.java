package com.zebone.modules.mobile.bd.ord.service;

import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdAlias;

import java.util.List;

public interface BdOrdService {

    /**
     * 检索非药品
     * @param spCode
     * @return
     */
    List<BdOrdAlias> search(String spCode);
}
