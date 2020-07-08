package com.zebone.modules.mobile.patient.dao;


import com.zebone.common.entity.pi.PiCate;
import com.zebone.modules.mobile.patient.vo.PiCateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PiCateDao {

    List<PiCateVO> listPiCate();
}