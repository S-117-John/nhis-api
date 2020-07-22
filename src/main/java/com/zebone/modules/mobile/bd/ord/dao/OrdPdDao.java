package com.zebone.modules.mobile.bd.ord.dao;

import com.zebone.modules.mobile.bd.ord.vo.OrdPdVO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
    /**
     * 获取检查检验详情
     * @param pkOrd
     * @return
     */
    List<Map<String,Object>> getLisOrRisDetailByPk(String pkOrd);
    
    /**
     * 获取执行科室列表
     * @param pkOrd
     * @return
     */
    List<Map<String,Object>> getOrdExDeptByPk(String pkOrd);
    /**
     * 查询检验检查类型
     * @param pkOrd
     * @return
     */
    List<Map<String,Object>> getLisOrRisTypeByCode(String code);
}
