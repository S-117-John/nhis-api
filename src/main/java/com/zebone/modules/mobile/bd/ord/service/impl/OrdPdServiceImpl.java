package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.modules.mobile.bd.encoderule.service.BdEncoderuleService;
import com.zebone.modules.mobile.bd.ord.dao.OrdPdDao;
import com.zebone.modules.mobile.bd.ord.service.OrdPdService;
import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrdPdServiceImpl implements OrdPdService {

    @Autowired
    private OrdPdDao ordPdDao;
    
    @Autowired
    private BdEncoderuleService bdEncoderuleService;
    @Override
    public List<OrdPdVO> listOrd(String spCode) {
        return ordPdDao.listOrd(spCode);
    }

    @Override
    public List<OrdPdVO> listPd(String spCode) {
        return ordPdDao.listPd(spCode);
    }
    /**
     * 获取检查检验详情和执行科室
     */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, List> getLisOrRisDetail(String pkOrd,String LisOrRis) {
		Map<String, List> resultMap=new HashMap<String, List>();
		//检查类型030100
		//标本类型030200
		//此次等待反查类型，待生成申请单号
		List<Map<String,Object>>  dataList=ordPdDao.getLisOrRisDetailByPk(pkOrd);
		resultMap.put("dataList", dataList);//返回检查检验详情
		resultMap.put("exDeptList", ordPdDao.getOrdExDeptByPk(pkOrd));//返回执行科室
		String code=null;
		String codeType=null;
		if("02".equals(LisOrRis)){
			code="030100";
			codeType="0402";
		}else if("03".equals(LisOrRis)){
			code="030200";
			codeType="0401";
		}
		if(null!=code && !"".equals(code)){
			resultMap.put("listType", ordPdDao.getLisOrRisTypeByCode(code));
			List<String> list=new ArrayList<String>();
			list.add(bdEncoderuleService.getEncoderuleByCode(codeType));
			resultMap.put("codeApple", list);
		}
		return resultMap;
	}
}
