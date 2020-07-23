package com.zebone.modules.mobile.bd.encoderule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zebone.common.entity.bd.encoderule.BdEncoderule;

@Mapper
public interface BdEncoderuleDao {
	/**
	 * 获取编码规则
	 * @param code
	 * @return
	 */
	BdEncoderule getEncoderuleByCode(String code);

	Integer updateByPk(BdEncoderule bdEncoderule);
}
