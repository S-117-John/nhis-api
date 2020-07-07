package com.zebone.modules.mobile.bd.dao;

import com.zebone.modules.mobile.bd.vo.OrdPdVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdPdDao {

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
}
