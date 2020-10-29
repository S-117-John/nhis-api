package com.zebone.modules.mobile.cn.service.impl;

import com.zebone.common.entity.bd.BdDefdoc;
import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdDept;
import com.zebone.common.entity.bd.ord.BdOrdLab;
import com.zebone.common.entity.bd.ord.BdOrdRis;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.term.BdTermFreq;
import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.modules.mobile.bd.encoderule.service.BdEncoderuleService;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdDeptRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdLisRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdRisRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuUserRepository;
import com.zebone.modules.mobile.bd.repository.BdDefdocRepository;
import com.zebone.modules.mobile.bd.term.repository.BdTermFreqRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.model.CnOrderParam;
import com.zebone.modules.mobile.cn.repository.CnLabApplyRepository;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.repository.CnRisApplyRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.service.CnOrderBaseService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.listener.ResultListener;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("cnOrdLisServiceImpl")
public class CnOrdLisServiceImpl extends CnOrderBaseService implements CnOrdService {

    @Autowired
    private BdOrdRepository bdOrdRepository;

    @Autowired
    private BdOrdLisRepository bdOrdLisRepository;

    @Autowired
    private BdDefdocRepository bdDefdocRepository;

    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;

    @Autowired
    private BdOrdDeptRepository bdOrdDeptRepository;


    @Autowired
private BdTermFreqRepository bdTermFreqRepository;

    @Autowired
    private BdOrdRisRepository bdOrdRisRepository;

    @Autowired
    private BdEncoderuleService bdEncoderuleService;



    @Autowired
    private PatientService patientService;

    @Autowired
    private BdOuUserRepository bdOuUserRepository;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Autowired
    private CnLabApplyRepository cnLabApplyRepository;




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

    /**
     * 查询停嘱列表
     *
     * @param pkCnord
     * @return
     */
    @Override
    public List<CnOrderVO> queryOrdStopListByPk(String pkCnord) {
        return null;
    }

    /**
     * 查询医嘱是否可以签署
     *
     * @param pkCnord
     * @return
     */
    @Override
    public Integer checkSignOrd(String pkCnord) {
        return null;
    }

    /**
     * 医嘱签署
     *
     * @param pkCnord@return
     */
    @Override
    public Integer signOrd(String pkCnord) {
        return null;
    }

    /**
     * 查询医嘱是否可以删除
     *
     * @param pkCnord
     * @return
     */
    @Override
    public Integer checkDelOrd(String pkCnord) {
        return null;
    }

    /**
     * 删除医嘱
     *
     * @param pkCnord
     * @return
     */
    @Override
    public Integer delOrd(String pkCnord) {
        return null;
    }

    /**
     * 抗菌药物保存
     *
     * @param ordList
     * @param pkOrg
     */
    @Override
    public List<CnOrdAnti> saveOrdAnti(List<CnOrder> ordList, String pkOrg) {
        return null;
    }

    /**
     * 保存检验申请
     *
     * @param user
     * @param saveLisList
     * @param pvEncounterVO
     * @param dept
     */
    @Override
    public List<CnOrder> saveLisApplyList(BdOuUser user, List<CnLabApplyVo> saveLisList, PvEncounterVO pvEncounterVO, BdOuDept dept) {
        return null;
    }

    /**
     * 保存检查申请
     *
     * @param user
     * @param saveRisList
     * @param pvEncounterVO
     * @param dept
     */
    @Override
    public List<CnOrder> saveRisApplyList(BdOuUser user, List<CnRisApplyVo> saveRisList, PvEncounterVO pvEncounterVO, BdOuDept dept) {
        return null;
    }

    /**
     * 设置医嘱的值
     *
     * @param cnOrders
     * @param bdPdList
     * @param pvEncounterVO
     * @param user
     * @return
     */
    @Override
    public List<CnOrder> setSaveCnOrder(List<CnOrder> cnOrders, List<Map<String, Object>> bdPdList, PvEncounterVO pvEncounterVO, BdOuUser user) {
        return null;
    }

    /**
     * 保存医嘱数据
     *
     * @param cnOrders
     * @param saveType
     * @param user
     */
    @Override
    public void saveOrdCnOrder(List<CnOrder> cnOrders, String saveType, BdOuUser user) {

    }

    /**
     * 停止医嘱
     *
     * @param cnOrders
     */
    @Override
    public void stop(List<CnOrder> cnOrders) {

    }

    /**
     * 签署医嘱
     *
     * @param cnOrders
     */
    @Override
    public void sign(List<CnOrder> cnOrders) {

    }

    /**
     * 查询对应执行科室
     *
     * @param deptCode
     * @return
     */
    @Override
    public String pkDeptExe(String deptCode) {
        return null;
    }

    /**
     * 查询对应执行科室
     *
     * @param deptCode
     * @return
     */
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
            if(pvEncounterVO==null){
                listener.error(String.format("根据住院号:%s未查询到就诊信息",cnOrderParam.getCodeIp()));
                return;
            }
            BdOrd bdOrd = bdOrdRepository.getOne(cnOrderParam.getPkOrd());
            if(bdOrd==null){
                listener.error(String.format("根据医嘱项目主键:%s未查询到医嘱项目信息",cnOrderParam.getPkOrd()));
                return;
            }
            BdOuUser bdOuUser = bdOuUserRepository.findByCodeUser(cnOrderParam.getDoctorCode());
            if(bdOuUser==null){
                listener.error(String.format("根据医生工号:%s未查询到医生信息",cnOrderParam.getDoctorCode()));
                return;
            }
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
            cnOrder.setQuan(Double.valueOf("1"));

            BdTermFreq bdTermFreq = bdTermFreqRepository.findByName("ONCE");

            if(bdTermFreq==null){
                bdTermFreq = bdTermFreqRepository.findByName("once");
            }
            if(bdTermFreq==null){
                listener.error(String.format("根据频次名称ONCE未查询到频次信息"));
                return;
            }

            cnOrder.setCodeFreq(bdTermFreq.getCode());

            cnOrder = cnOrderRepository.save(cnOrder);

            CnLabApply cnLabApply = new CnLabApply();
            cnLabApply.setPkOrg(pvEncounterVO.getPkOrg());
//            cnRisApply.setPkOrdris(UUID.randomUUID().toString().replaceAll("-", ""));
            cnLabApply.setPkCnord(cnOrder.getPkCnord());
            cnLabApply.setEuStatus("0");
            cnLabApply.setTs(d);
//            cnRisApply.setDescBody(cnOrderParam.getDescBody());
            cnLabApply.setPurpose(cnOrderParam.getPurpose());
//            cnRisApply.setNoteDise(cnOrderParam.getNoteDise());
            cnLabApply.setNote(cnOrderParam.getNote());
            cnLabApplyRepository.save(cnLabApply);
            listener.success("SUCCESS");
        }catch (Exception exception){
            listener.exception(exception.getMessage());
        }
    }

    @Override
    public void getOne(String id, ResultListener listener) {
        try {
            BdOrd bdOrd = bdOrdRepository.getOne(id);
            CnOrderVO cnOrderVO = new CnOrderVO();
            cnOrderVO.setPkOrd(bdOrd.getPkOrd());
            cnOrderVO.setNameOrd(bdOrd.getName());
            List<BdOrdLab> bdOrdLabList = bdOrdLisRepository.findByPkOrd(bdOrd.getPkOrd());
            if(bdOrdLabList.size()<=0){
                listener.error("未查询到BdOrdRis");
                return;
            }
            if(bdOrdLabList.size()>1){
                listener.error("查询到多个BdOrdRis");
                return;
            }
            BdOrdLab bdOrdLab = bdOrdLabList.get(0);
//            List<BdDefdoc> bdDefdocList =
//                    bdDefdocRepository.findByCodeAndCodeDefdoclist(bdOrdLab.getDtType(),"030100");
//            if(bdDefdocList.size()<=0){
//                listener.error("未查询到BdDefdoc");
//                return;
//            }

//            if(bdDefdocList.size()>1){
//                listener.error("查询到多个BdDefdoc");
//                return;
//            }
//            BdDefdoc bdDefdoc = bdDefdocList.get(0);
//            cnOrderVO.setNameType(bdDefdoc.getName());
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

            //标本类型
            List<BdDefdoc> bdDefdocList = bdDefdocRepository.findByCodeAndCodeDefdoclist(bdOrdLab.getDtSamptype(),"030200");
            bdDefdocList.forEach(bdDefdoc -> {cnOrderVO.setSpecimenType(bdDefdoc.getName());});

            listener.success(cnOrderVO);
        }catch (Exception exception){
            listener.exception(exception.getMessage());
        }
    }
}
