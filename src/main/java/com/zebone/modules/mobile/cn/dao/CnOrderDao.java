package com.zebone.modules.mobile.cn.dao;



import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CnOrderDao {

    List<CnOrderVO> listPatientOrder(String pkPv);
    /**
     * 查询是否可以停止医嘱
     * @param pkCnord
     * @return
     */
    Integer checkStopOrd(String pkCnord);
    /**
     * 查询停嘱列表
     * @param pkCnord
     * @return
     */
    List<CnOrderVO> queryOrdStopListByPk(String pkCnord);
    
    /**
     * 查询医嘱是否可以签署
     * @param pkCnord
     * @return
     */
    Integer checkSignOrd(String pkCnord);
    /**
     * 医嘱签署
     * @param param
     * @return
     */
    Integer signOrd(String pkCnord);
    /**
     * 查询医嘱是否可以删除
     * @param pkCnord
     * @return
     */
    Integer checkDelOrd(String pkCnord);
    /**
     * 删除医嘱
     * @param pkCnord
     * @return
     */
    Integer delOrd(String pkCnord);

    Double selectSn(@Param("tableName")String tableName, @Param("fieldName")String fieldName);

    int updateSn(@Param("tableName")String tableName,@Param("fieldName")String fieldName,@Param("count")int count);

    int initSn(BdSerialno initSn);
}