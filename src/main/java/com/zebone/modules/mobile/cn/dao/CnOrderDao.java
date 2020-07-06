package com.zebone.modules.mobile.cn.dao;



import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CnOrderDao {

    List<CnOrderVO> listPatientOrder(String pkPv);
}