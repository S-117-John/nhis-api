package com.zebone.modules.mobile.cn.service.impl;

import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.term.BdTermFreq;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuUserRepository;
import com.zebone.modules.mobile.bd.term.repository.BdTermFreqRepository;
import com.zebone.modules.mobile.cn.model.CnOrderParam;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.OrdUtils;
import com.zebone.modules.mobile.common.listener.ResultListener;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 治疗医嘱实现
 */
@Service("cnOrdTreatmentService")
public class CnOrdTreatmentServiceImpl implements CnOrdService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private BdOrdRepository bdOrdRepository;

    @Autowired
    private BdOuUserRepository bdOuUserRepository;
    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Autowired
    private BdTermFreqRepository bdTermFreqRepository;

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

    /**
     * 保存医嘱
     * @param cnOrders
     * @param saveType
     * @param user
     */
    @Override
    public void saveOrdCnOrder(List<CnOrder> cnOrders, String saveType, BdOuUser user) {
        if(cnOrders!=null && cnOrders.size()>0){
            cnOrders.forEach(cnOrder -> {
                cnOrder.setCreateTime(new Date());
                cnOrder.setFlagErase("0");
                cnOrder.setFlagNote("0");//是否嘱托
                cnOrder.setFlagBl("1");//是否计费
                cnOrder.setFlagDoctor("1");
                //非药品
                cnOrder.setFlagDurg("0");
                //特殊频次设置长期临时医嘱,ST\ONCE时为临时医嘱
                if("146".equals(cnOrder.getCodeFreq())||"191".equals(cnOrder.getCodeFreq())){
                    cnOrder.setEuAlways("1");
                }
            });
            cnOrderRepository.saveAll(cnOrders);
        }
    }

    @Override
    public void stop(List<CnOrder> cnOrders) {

    }

    @Override
    public void sign(List<CnOrder> cnOrders) {
        if(cnOrders!=null && cnOrders.size()>0){
            cnOrders.forEach(cnOrder -> {
                if("05".equals(cnOrder.getCodeOrdtype())){
                    cnOrder.setFlagSign("1");
                    cnOrder.setDateSign(new Date());
                    cnOrder.setEuStatusOrd("1");
                    cnOrder.setFlagErase("0");
                    cnOrder.setFlagDoctor("1");//医生是1，护士是0
                    cnOrder.setCreateTime(new Date());
                    cnOrder.setFlagNote("0");//是否嘱托
                    cnOrder.setFlagBl("1");//是否计费
                    //非药品
                    cnOrder.setFlagDurg("0");
                    //特殊频次设置长期临时医嘱,ST\ONCE时为临时医嘱
                    if("1049".equals(cnOrder.getCodeFreq())||"1012".equals(cnOrder.getCodeFreq())){
                        cnOrder.setEuAlways("1");
                    }
                }
            });
        }

    }

    @Override
    public String pkDeptExe(String deptCode) {
        return null;
    }

    @Override
    public List<BdOuDept> getExeDeptList(String deptCode) {
        return null;
    }

    @Override
    public void save(Object object, ResultListener listener) {
        CnOrderParam cnOrderParam = (CnOrderParam) object;
        //查询医生信息
        BdOuUser user = bdOuUserRepository.findByCodeUser(cnOrderParam.getDoctorCode());
        //查询患者信息
        PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParam.getCodeIp());
        //查询医嘱信息
        BdOrd bdOrd = bdOrdRepository.getOne(cnOrderParam.getPkOrd());
        BdOuDept bdOuDept = bdOuDeptRepository.findByCodeDept(cnOrderParam.getCodeDept());
        CnOrder cnOrder = new CnOrder();
        cnOrder.setPkOrg(user.getPkOrg());
        cnOrder.setEuPvtype("3");
        cnOrder.setPkPv(pvEncounterVO.getPkPv());
        cnOrder.setPkPi(pvEncounterVO.getPkPi());
        cnOrder.setCodeOrdtype(bdOrd.getCodeOrdtype());
        Integer ordSn = OrdUtils.getSerialNo("CN_ORDER", "ORDSN", 1);
        cnOrder.setOrdsn(ordSn);
        cnOrder.setPkOrd(bdOrd.getPkOrd());
        cnOrder.setCodeOrd(bdOrd.getCode());
        cnOrder.setNameOrd(bdOrd.getName());
        cnOrder.setEuAlways(cnOrderParam.getEuAlways());
        //频次
        BdTermFreq bdTermFreq = bdTermFreqRepository.findByCode(cnOrderParam.getCodeFreq());
        if("st".equalsIgnoreCase(bdTermFreq.getName())||"once".equalsIgnoreCase(bdTermFreq.getName())){
            cnOrder.setEuAlways("1");
        }
        cnOrder.setCodeFreq(cnOrderParam.getCodeFreq());
        cnOrder.setSpec(bdOrd.getSpec());
        cnOrder.setQuan(cnOrderParam.getQuan());
        cnOrder.setPkUnit(bdOrd.getPkUnit());
        cnOrder.setFlagFirst("0");
        cnOrder.setPkOrgExec(user.getPkOrg());
        cnOrder.setPkDeptExec(cnOrderParam.getPkDeptExec());
        //0 开立；1 签署；2 核对；3 执行；4 停止；9 作废
        cnOrder.setEuStatusOrd("0");
        cnOrder.setDateEnter(new Date());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isEmpty(cnOrderParam.getDateStart())){
                cnOrder.setDateStart(new Date());
            }else {
                cnOrder.setDateStart(simpleDateFormat.parse(cnOrderParam.getDateStart()));
            }
        } catch (ParseException e) {
            listener.exception(e.getMessage());
            return;
        }
        cnOrder.setFlagDurg("0");
        cnOrder.setFlagSelf("0");
        cnOrder.setFlagNote("0");
        cnOrder.setFlagBase("0");
        cnOrder.setFlagBl("1");
        cnOrder.setPkDept(pvEncounterVO.getPkDept());
        cnOrder.setNameEmpInput(user.getPkEmp());
        cnOrder.setNameEmpInput(user.getNameUser());
        cnOrder.setPkEmpOrd(user.getPkEmp());
        cnOrder.setNameEmpOrd(user.getNameUser());
        //1表示医生医嘱，0表示护士医嘱
        cnOrder.setFlagDoctor("1");
        cnOrder.setCreator(user.getPkEmp());
        cnOrder.setCreateTime(new Date());
        cnOrder.setTs(new Date());
        //作废标志
        cnOrder.setFlagErase("0");
        //0:普通，1：科研
        cnOrder.setEuOrdtype("0");
        //签署
        cnOrder.setFlagSign("0");
        //删除标志
        cnOrder.setDelFlag("0");
        //pc端用量
        cnOrder.setDosage(cnOrderParam.getQuan());
        //首日次数
        cnOrder.setFirstNum(cnOrderParam.getFirstNum());
        cnOrderRepository.save(cnOrder);
    }
}
