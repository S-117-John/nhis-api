package com.zebone.modules.mobile.cn.service;



import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;

import java.util.List;

public interface CnOrdService {

    List<CnOrderVO> listPatientOrder(String pkPv);

    void save(List<CnOrder> cnOrders);
}
