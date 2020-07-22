package com.zebone.modules.mobile.bd.ord.service;

import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;

import java.util.List;
import java.util.Map;

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
    /**
     * 获取检查检验详情和执行科室
     * @param pkOrd
     * @return
     */
    @SuppressWarnings("rawtypes")
	Map<String, List> getLisOrRisDetail(String pkOrd,String LisOrRis);
}
