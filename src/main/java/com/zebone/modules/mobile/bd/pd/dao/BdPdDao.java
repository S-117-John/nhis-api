package com.zebone.modules.mobile.bd.pd.dao;


import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BdPdDao {

    /**
     * 查询药品信息
     * @param pkPd
     * @return
     */
    BdPdVO getBdPd(String pkPd);
}