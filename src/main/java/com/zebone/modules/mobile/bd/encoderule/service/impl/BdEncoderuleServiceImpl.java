package com.zebone.modules.mobile.bd.encoderule.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zebone.common.entity.bd.encoderule.BdEncoderule;
import com.zebone.modules.mobile.bd.encoderule.dao.BdEncoderuleDao;
import com.zebone.modules.mobile.bd.encoderule.service.BdEncoderuleService;

@Service
public class BdEncoderuleServiceImpl implements BdEncoderuleService{

	@Autowired
	private BdEncoderuleDao bdEncoderuleDao;
	/**
	 * 生成申请单号
	 */
	@Override
	public String getEncoderuleByCode(String code) {
		// TODO Auto-generated method stub
		if(null==code || "".equals(code)){
			return null;
		}
		BdEncoderule bdEncoderule=bdEncoderuleDao.getEncoderuleByCode(code);
		if (bdEncoderule == null) {
			return null;
		}
		StringBuffer bh = new StringBuffer();
		Date nowDate = new Date();
		String suffix="";
		if ("1".equals(bdEncoderule.getFlagPrefix()) && bdEncoderule.getPrefix() != null) {
			bh.append(bdEncoderule.getPrefix());
		}
		if ("1".equals(bdEncoderule.getFlagSuffix()) && bdEncoderule.getSuffix() != null) {
			suffix=bdEncoderule.getSuffix();
		}

		if ("1".equals(bdEncoderule.getFlagDate())) {
			SimpleDateFormat dateformat = new SimpleDateFormat(bdEncoderule.getFormatDate());
			bh.append(dateformat.format(nowDate));
		}

		long val = bdEncoderule.getVal();
		if (val == 0) {
			val = bdEncoderule.getValInit();
		} else {
			val = val + 1;
			if ("3".equals(bdEncoderule.getEuSnrule())) { // 按年归零
				if (!com.zebone.common.support.DateUtils.isSameYear(bdEncoderule.getTs(), nowDate)) {
					val = bdEncoderule.getValInit();
				}
			} else if ("2".equals(bdEncoderule.getEuSnrule())) { // 按年归零
				if (!com.zebone.common.support.DateUtils.isSameMonth(bdEncoderule.getTs(), nowDate)) {
					val = bdEncoderule.getValInit();
				}
			} else if ("1".equals(bdEncoderule.getEuSnrule())) {// 按日归零
				if (!com.zebone.common.support.DateUtils.isSameDay(bdEncoderule.getTs(), nowDate)) {
					val = bdEncoderule.getValInit();
				}
			}
		}
		int bhLen = bh.length();
		int suffixLen=suffix.length();
		int addCodeSize = bdEncoderule.getLengthCode() - bhLen - String.valueOf(val).length()-suffixLen;

		if (addCodeSize < 0) {
			return null;
		}

		for (int i = 0; i < addCodeSize; i++) {
			bh.append("0");
		}

		bh.append(val);
		bh.append(suffix);
		bdEncoderule.setTs(nowDate);
		bdEncoderule.setVal(val);
		
		bdEncoderuleDao.updateByPk(bdEncoderule);
		return bh.toString();
	}

}
