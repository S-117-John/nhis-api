package com.zebone.modules.mobile.cn.service.impl;

import com.zebone.common.entity.bd.BdDefdoc;
import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdDept;
import com.zebone.common.entity.bd.ord.BdOrdRis;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.modules.mobile.bd.encoderule.service.BdEncoderuleService;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdDeptRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdRisRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuUserRepository;
import com.zebone.modules.mobile.bd.repository.BdDefdocRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.model.CnOrderParam;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.repository.CnRisApplyRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.listener.ResultListener;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("cnOrdRisServiceImpl")
public class CnOrdRisServiceImpl implements CnOrdService {

    @Autowired
    private BdOrdRepository bdOrdRepository;

    @Autowired
    private BdDefdocRepository bdDefdocRepository;

    @Autowired
    private BdOrdRisRepository bdOrdRisRepository;

    @Autowired
    private BdEncoderuleService bdEncoderuleService;

    @Autowired
    private BdOrdDeptRepository bdOrdDeptRepository;

    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BdOuUserRepository bdOuUserRepository;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Autowired
    private CnRisApplyRepository cnRisApplyRepository;

    @Autowired
    private CnOrderDao cnOrderDao;

    @Override
    public void saveAndSign(CnOrderParam cnOrderParam, ResultListener resultListener) {

    }

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
        if(tableName==null) {
            return 0;
        }
        Double sn = cnOrderDao.selectSn(tableName.toUpperCase(), fieldName.toUpperCase());
        if(sn==null){
            BdSerialno initSn = new BdSerialno();
            initSn.setPkSerialno(UUID.randomUUID().toString());
            initSn.setPkOrg("~                               ");
            initSn.setNameTb(tableName.toUpperCase());
            initSn.setNameFd(fieldName.toUpperCase());
            initSn.setValInit((short)1000);
            initSn.setVal((short)1000);
            cnOrderDao.initSn(initSn);
        }
        int ret = -1;
        int rs = cnOrderDao.updateSn(tableName.toUpperCase(), fieldName.toUpperCase(), count);
        if(rs==1) {
            ret = cnOrderDao.selectSn(tableName.toUpperCase(), fieldName.toUpperCase()).intValue()-count;
        }
        return ret;
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

    @Override
    public void save(Object object, ResultListener listener) {
        try{
            CnOrderParam cnOrderParam = (CnOrderParam) object;
            Date d = new Date();
            Integer orderSn = getSerialNo("CN_ORDER", "ORDSN", 1);
            CnOrder cnOrder = new CnOrder();
            PvEncounterVO pvEncounterVO = patientService.getPatientInfo(cnOrderParam.getCodeIp());
            BdOrd bdOrd = bdOrdRepository.getOne(cnOrderParam.getPkOrd());
            BdOuUser bdOuUser = bdOuUserRepository.findByCodeUser(cnOrderParam.getDoctorCode());

            cnOrder.setPkCnord(UUID.randomUUID().toString().replaceAll("-", ""));
            cnOrder.setOrdsn(orderSn);
            cnOrder.setOrdsnParent(orderSn);
            cnOrder.setInfantNo(0);
            cnOrder.setPkPv(pvEncounterVO.getPkPv());
            cnOrder.setCodeOrdtype(bdOrd.getCodeOrdtype());
            cnOrder.setPkOrd(bdOrd.getPkOrd());
            cnOrder.setCodeOrd(bdOrd.getCode());
            cnOrder.setNameOrd(bdOrd.getName());
            cnOrder.setPkDeptNs(pvEncounterVO.getPkDeptNs());
            cnOrder.setFlagBl("0");
            cnOrder.setFlagErase("0");
            cnOrder.setTs(d);
            if(StringUtils.isEmpty(cnOrderParam.getDateStart())){
                cnOrder.setDateStart(d);
            }else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    cnOrder.setDateStart(simpleDateFormat.parse(cnOrderParam.getDateStart()));
                } catch (ParseException e) {
                    cnOrder.setDateStart(d);
                }
            }
            cnOrder.setPkOrg(pvEncounterVO.getPkOrg());
            cnOrder.setCodeApply(cnOrderParam.getCodeApply());
            cnOrder.setPkPi(pvEncounterVO.getPkPi());
            cnOrder.setEuOrdtype("0"); // 科研医嘱标志
            cnOrder.setFlagFit("0");// 适应症标志
            cnOrder.setPkEmpInput(bdOuUser.getPkEmp());
            cnOrder.setNameEmpInput(bdOuUser.getNameUser());
            cnOrder.setEuAlways("1");
            cnOrder.setEuStatusOrd("0");		//保存
            cnOrder.setFlagDurg("0");
            cnOrder.setFlagFirst("0");
            cnOrder.setFlagSign("0");
            cnOrder.setFlagSelf("0");
            cnOrder.setFlagNote("0");
            cnOrder.setFlagBase("0");
            cnOrder.setFlagStop("0");
            cnOrder.setFlagStopChk("0");
            cnOrder.setFlagErase("0");
            cnOrder.setFlagEraseChk("0");
            cnOrder.setFlagCp("0");
            cnOrder.setFlagDoctor("1");
            cnOrder.setFlagPrint("0");
            cnOrder.setFlagMedout("0");
            cnOrder.setFlagEmer("0");
            cnOrder.setFlagThera("0");
            cnOrder.setFlagPrev("0");
            cnOrder.setDelFlag("0");
            cnOrder.setEuPvtype("3");
            cnOrder.setFlagEmer("0");
            cnOrder.setPkOrgExec(pvEncounterVO.getPkOrg());
            cnOrder.setPkDeptExec(cnOrderParam.getPkDeptExec());
            cnOrder.setOrdsnChk(cnOrder.getOrdsn());
            cnOrder.setPkDept(pvEncounterVO.getPkDept());
            cnOrder.setNameEmpOrd(bdOuUser.getNameUser());
            cnOrder.setPkEmpOrd(bdOuUser.getPkEmp());
            cnOrderRepository.save(cnOrder);

            CnRisApply cnRisApply = new CnRisApply();
            cnRisApply.setPkOrg(pvEncounterVO.getPkOrg());
            cnRisApply.setPkOrdris(UUID.randomUUID().toString().replaceAll("-", ""));
            cnRisApply.setPkCnord(cnOrder.getPkCnord());
            cnRisApply.setEuStatus("0");
            cnRisApply.setTs(d);
            cnRisApply.setDescBody(cnOrderParam.getDescBody());
            cnRisApply.setPurpose(cnOrderParam.getPurpose());
            cnRisApply.setNoteDise(cnOrderParam.getNoteDise());
            cnRisApply.setNote(cnOrderParam.getNote());
            cnRisApplyRepository.save(cnRisApply);
            listener.success("SUCCESS");
        }catch (Exception exception){
            listener.exception(exception.getMessage());
        }

    }

    /**
     * 获取检查项目信息
     * @param id
     * @param listener
     */
    @Override
    public void getOne(String id, ResultListener listener) {

        try {
            BdOrd bdOrd = bdOrdRepository.getOne(id);
            CnOrderVO cnOrderVO = new CnOrderVO();
            cnOrderVO.setPkOrd(bdOrd.getPkOrd());
            cnOrderVO.setNameOrd(bdOrd.getName());
            List<BdOrdRis> bdOrdRisList = bdOrdRisRepository.findByPkOrd(bdOrd.getPkOrd());
            if(bdOrdRisList.size()<=0){
                listener.error("未查询到BdOrdRis");
                return;
            }
            if(bdOrdRisList.size()>1){
                listener.error("查询到多个BdOrdRis");
                return;
            }
            BdOrdRis bdOrdRis = bdOrdRisList.get(0);
            List<BdDefdoc> bdDefdocList =
                    bdDefdocRepository.findByCodeAndCodeDefdoclist(bdOrdRis.getDtType(),"030100");
            if(bdDefdocList.size()<=0){
                listener.error("未查询到BdDefdoc");
                return;
            }

            if(bdDefdocList.size()>1){
                listener.error("查询到多个BdDefdoc");
                return;
            }
            BdDefdoc bdDefdoc = bdDefdocList.get(0);
            cnOrderVO.setNameType(bdDefdoc.getName());
            String codeApply = bdEncoderuleService.getEncoderuleByCode("0402");
            cnOrderVO.setCodeApply(codeApply);
            //查询执行科室
            List<BdOrdDept> bdOrdDeptList = bdOrdDeptRepository.findByPkOrd(bdOrd.getPkOrd());
            if(bdOrdDeptList.size()==0){
                listener.error("未查询到BdOrdDept");
                return;
            }
            List<String> pkDeptList = new ArrayList<>();
            bdOrdDeptList.forEach(bdOrdDept -> {
                pkDeptList.add(bdOrdDept.getPkDept());
            });
            List<BdOuDept> bdOuDeptList = bdOuDeptRepository.findAllById(pkDeptList);
            if(bdOuDeptList.size()==0){
                listener.error("没有查询到执行科室信息");
                return;
            }
            cnOrderVO.setDeptList(bdOuDeptList);
            listener.success(cnOrderVO);
        }catch (Exception exception){
            listener.exception(exception.getMessage());
        }
    }
}
