package com.zebone.modules.mobile.bd.service;


import com.zebone.modules.mobile.bd.vo.BdOrdSetVO;

import java.util.List;

public interface BdOrdSetService {

    /**
     * 查询个人模板
     * @param pkEmp
     * @return
     */
    List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp);
}
