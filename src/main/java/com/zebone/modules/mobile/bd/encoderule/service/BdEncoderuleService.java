package com.zebone.modules.mobile.bd.encoderule.service;

public interface BdEncoderuleService {
	/**
	 * 根据编码生成单号
	 * @param code
	 * @return
	 */
	String getEncoderuleByCode(String code);
}
