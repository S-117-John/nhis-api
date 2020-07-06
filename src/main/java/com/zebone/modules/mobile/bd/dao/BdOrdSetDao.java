package com.zebone.modules.mobile.bd.dao;

import com.zebone.modules.mobile.bd.vo.BdOrdSetVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BdOrdSetDao {

    /**
     * 获取个人模板
     * @param pkEmp
     * @return
     */
    List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp);
}