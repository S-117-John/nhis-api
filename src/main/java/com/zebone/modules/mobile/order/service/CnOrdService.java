package com.zebone.modules.mobile.order.service;

import com.zebone.modules.mobile.order.vo.CnOrderVO;

import java.util.List;

public interface CnOrdService {

    List<CnOrderVO> listPatientOrder(String pkPv);
}
