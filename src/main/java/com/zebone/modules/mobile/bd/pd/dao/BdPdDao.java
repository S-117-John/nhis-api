package com.zebone.modules.mobile.bd.pd.dao;


import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BdPdDao {

    /**
     * 查询药品信息
     * @param pkPd
     * @return
     */
    BdPdVO getBdPd(String pkPd);

    /**
     * 查询药品信息和药品属性
     * @param pkPd
     * @return
     */
    List<BdPdVO> getBdPdByPkPd(List<String> pkPd);

    List<Map<String,Object>> getBdPdAndBdOrdByPkPd(List<String> bdPds);
}