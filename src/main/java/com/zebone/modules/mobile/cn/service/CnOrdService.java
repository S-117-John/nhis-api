package com.zebone.modules.mobile.cn.service;



import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;

import java.util.List;

public interface CnOrdService {

    List<CnOrderVO> listPatientOrder(String pkPv);

    void save(List<CnOrder> cnOrders);

    /**
     * 医嘱详情
     * @param pkCnord
     * @return
     */
    CnOrderVO getCnOrderDetail(String pkCnord);

    /**
     * 检索医嘱
     * @param spCode
     * @return
     */
    List<CnOrderVO> search(String spCode);
}
