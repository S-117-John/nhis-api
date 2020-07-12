package com.zebone.modules.mobile.bd.ord.service;


import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdSetVO;

import java.util.List;

public interface BdOrdSetService {

    /**
     * 查询个人模板
     * @param pkEmp
     * @return
     */
    List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp);

    /**
     * 获取医嘱模板明细
     * @param pkOrdset
     * @return
     */
    List<BdOrdSetDt> listBdOrdSetDt(String pkOrdset);
}
