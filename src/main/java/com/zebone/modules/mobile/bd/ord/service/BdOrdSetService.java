package com.zebone.modules.mobile.bd.ord.service;


import com.zebone.modules.mobile.bd.ord.vo.BdOrdSetVO;

import java.util.List;

public interface BdOrdSetService {

    /**
     * 查询个人模板
     * @param pkEmp
     * @return
     */
    List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp);
}
