package com.zebone.modules.mobile.bd.pd.service;

import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;

public interface BdPdService {

    /**
     * 检索药品信息
     * @param pkPd
     * @return
     */
    BdPdVO getBdPd(String pkPd);
}
