package com.zebone.modules.mobile.cn.service;



import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;

import java.util.List;
import java.util.Map;

public interface CnOrdService {

    List<CnOrderVO> listPatientOrder(String pkPv);

    void save(List<CnOrder> cnOrders);

    /**
     * 医嘱详情
     * @param pkCnord
     * @return
     */
    CnOrderVO getCnOrderDetail(String pkCnord);

    /**
     * 检索医嘱
     * @param spCode
     * @return
     */
    List<CnOrderVO> search(String spCode);
    
    /**
     * 检查是否可以停嘱
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
    Integer signOrd(Map<String,Object> param);
    
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

    /**
     * 生成医嘱号
     * @param tableName
     * @param fieldName
     * @param count
     * @return
     */
    Integer getSerialNo(String tableName, String fieldName, int count);

/*
    *//**
     * 保存检验
     * @param cnRisApply
     *//*
    void saveCnRisApply(List<CnRisApply> cnRisApply);

    *//**
     * 保存检查
     * @param cnLabApply
     *//*
    void saveCnLabApply(List<CnLabApply> cnLabApply);*/

    /**
     * 抗菌药物保存
     */
    List<CnOrdAnti> saveOrdAnti(List<CnOrder> ordList,String pkOrg);
    /**
     * 保存检验申请
     * @param user
     * @param saveLisList
     */
    List<CnOrder> saveLisApplyList(BdOuUser user, List<CnLabApplyVo> saveLisList,PvEncounterVO pvEncounterVO,BdOuDept dept);
    /**
     * 保存检查申请
     * @param user
     * @param saveLisList
     */
    List<CnOrder> saveRisApplyList(BdOuUser user, List<CnRisApplyVo> saveRisList,PvEncounterVO pvEncounterVO,BdOuDept dept);


    /**
     * 设置医嘱的值
     * @param cnOrders
     * @param bdPdList
     * @param pvEncounterVO
     * @param user
     * @return
     */
    List<CnOrder> setSaveCnOrder(List<CnOrder> cnOrders, List<Map<String,Object>> bdPdList,PvEncounterVO pvEncounterVO,BdOuUser user);

    /**
     * 保存医嘱数据
     * @param cnOrders
     * @param saveType
     * @param user
     */
    void saveOrdCnOrder(List<CnOrder> cnOrders,String saveType,BdOuUser user);
}
