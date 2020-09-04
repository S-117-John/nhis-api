package com.zebone.modules.mobile.cn.service.impl;

import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 治疗医嘱实现
 */
@Service("cnOrdTreatmentService")
public class CnOrdTreatmentServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return null;
    }

    @Override
    public List<CnOrder> list(String pkPv) {
        return null;
    }

    @Override
    public void save(List<CnOrder> cnOrders) {

    }

    @Override
    public CnOrderVO getCnOrderDetail(String pkCnord) {
        return null;
    }

    @Override
    public List<CnOrderVO> search(String spCode) {
        return null;
    }

    @Override
    public Integer checkStopOrd(String pkCnord) {
        return null;
    }

    @Override
    public List<CnOrderVO> queryOrdStopListByPk(String pkCnord) {
        return null;
    }

    @Override
    public Integer checkSignOrd(String pkCnord) {
        return null;
    }

    @Override
    public Integer signOrd(String pkCnord) {
        return null;
    }

    @Override
    public Integer checkDelOrd(String pkCnord) {
        return null;
    }

    @Override
    public Integer delOrd(String pkCnord) {
        return null;
    }

    @Override
    public Integer getSerialNo(String tableName, String fieldName, int count) {
        return null;
    }

    @Override
    public List<CnOrdAnti> saveOrdAnti(List<CnOrder> ordList, String pkOrg) {
        return null;
    }

    @Override
    public List<CnOrder> saveLisApplyList(BdOuUser user, List<CnLabApplyVo> saveLisList, PvEncounterVO pvEncounterVO, BdOuDept dept) {
        return null;
    }

    @Override
    public List<CnOrder> saveRisApplyList(BdOuUser user, List<CnRisApplyVo> saveRisList, PvEncounterVO pvEncounterVO, BdOuDept dept) {
        return null;
    }

    @Override
    public List<CnOrder> setSaveCnOrder(List<CnOrder> cnOrders, List<Map<String, Object>> bdPdList, PvEncounterVO pvEncounterVO, BdOuUser user) {
        return null;
    }

    @Override
    public void saveOrdCnOrder(List<CnOrder> cnOrders, String saveType, BdOuUser user) {
        if(cnOrders!=null && cnOrders.size()>0){
            cnOrders.forEach(cnOrder -> {
                cnOrder.setCreateTime(new Date());
                cnOrder.setFlagErase("0");
                cnOrder.setFlagNote("0");//是否嘱托
                cnOrder.setFlagBl("1");//是否计费
                //非药品
                cnOrder.setFlagDurg("0");
            });
            cnOrderRepository.saveAll(cnOrders);
        }
    }

    @Override
    public void stop(List<CnOrder> cnOrders) {

    }

    @Override
    public void sign(List<CnOrder> cnOrders) {

    }

    @Override
    public String pkDeptExe(String deptCode) {
        return null;
    }

    @Override
    public List<BdOuDept> getExeDeptList(String deptCode) {
        return null;
    }
}
