package com.zebone.modules.mobile.cn.service.impl;

import com.zebone.common.entity.bd.dept.BdDeptBu;
import com.zebone.common.entity.bd.dept.BdDeptBus;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.bd.dept.repository.BdDeptBuRepository;
import com.zebone.modules.mobile.bd.dept.repository.BdDeptBusRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuUserRepository;
import com.zebone.modules.mobile.bd.pd.repository.BdPdRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.model.CnOrderParam;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.common.listener.ResultListener;
import com.zebone.modules.mobile.patient.service.PatientService;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service("cnOrdDrugServiceImpl")
public class CnOrdDrugServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Autowired
    private BdPdRepository bdPdRepository;

    @Autowired
    private BdOrdTypeRepository bdOrdTypeRepository;

    @Autowired
    private BdOuUserRepository bdOuUserRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BdDeptBusRepository bdDeptBusRepository;

    @Autowired
    private BdDeptBuRepository bdDeptBuRepository;

    @Override
    public void saveAndSign(CnOrderParam cnOrderParam, ResultListener resultListener) {

        try{
            //医生编码
            String userCode = cnOrderParam.getDoctorCode();
            //住院号
            String codeIp = cnOrderParam.getCodeIp();
            //科室编码
            String codeDept = cnOrderParam.getCodeDept();
            List<String> pkCnOrders = cnOrderParam.getSaveOrderList();
            //查询医生信息
            BdOuUser user = bdOuUserRepository.findByCodeUser(userCode);
            //查询患者信息
            PvEncounterVO pvEncounterVO = patientService.getPatientInfo(codeIp);
            String pkDeptExe = pkDeptExe(codeDept);

            List<CnOrder> cnOrderList = cnOrderParam.getCnOrderList();
            Date date = new Date();

            for (int i = 0; i < cnOrderList.size(); i++) {
                CnOrder cnOrder = cnOrderList.get(i);
                cnOrder.setPkDeptExec(pkDeptExe);
                cnOrder.setPkPv(pvEncounterVO.getPkPv());
                cnOrder.setPkPi(pvEncounterVO.getPkPi());
                cnOrder.setPkEmpInput(user.getPkEmp());
                cnOrder.setNameEmpInput(user.getNameUser());
                cnOrder.setPkDept(pvEncounterVO.getPkDept());
                cnOrder.setPkDeptNs(pvEncounterVO.getPkDeptNs());
                cnOrder.setCreator(user.getPkEmp());
                Integer ordsn = 0;
                if(i ==0){
                    ordsn = getSerialNo("CN_ORDER", "ORDSN", 1);
                    cnOrder.setOrdsn(ordsn);
                    cnOrder.setOrdsnParent(ordsn);
                }else{
                    cnOrder.setOrdsn(getSerialNo("CN_ORDER", "ORDSN", 1));
                    cnOrder.setOrdsnParent(ordsn);
                }
                cnOrder.setTs(date);
                cnOrder.setDateEnter(date);
                cnOrder.setOrdsnChk(cnOrder.getOrdsn());
                cnOrder.setCreateTime(date);
                cnOrder.setDelFlag("0");
                cnOrder.setTs(date);
                BdPd bdPd = bdPdRepository.getOne(cnOrder.getPkOrd());
                BdOrdType bdOrdType = bdOrdTypeRepository.getOne(bdPd.getPkOrdtype());
                cnOrder.setCodeOrdtype(bdOrdType.getCode());
                cnOrder.setCodeOrd(bdPd.getCode());
                cnOrder.setNameOrd(bdPd.getName());
                cnOrder.setSpec(bdPd.getSpec());
                cnOrder.setPkUnitDos(bdPd.getPkUnitMin());
                if(bdPd.getPackSize()!= null&& Double.valueOf(bdPd.getPackSize())!=0 &&
                        "0".equals(cnOrder.getEuAlways())&&
                        !StringUtil.isNullOrEmpty(bdPd.getPkPd())){
                    double execTimes = 0;
                    if("0".equals(bdPd.getEuMuputype())){//按次取整
                        cnOrder.setQuanCg(Math.floor(Double.valueOf(bdPd.getPackSize())/(Double.valueOf(bdPd.getPackSize()) > 1 ? 1 : Double.valueOf(bdPd.getPackSize())))*execTimes);
                    }else{ //按批取整
                        cnOrder.setQuanCg(Math.floor(Double.valueOf(bdPd.getPackSize())/(Double.valueOf(bdPd.getPackSize()) > 1 ? 1 : Double.valueOf(bdPd.getPackSize())))*execTimes);
                    }
                }
                cnOrder.setPackSize(Double.valueOf(bdPd.getPackSize()==null?"0":bdPd.getPackSize().toString()));
                cnOrder.setPriceCg(Double.valueOf(bdPd.getPrice()==null?"0":bdPd.getPrice().toString()));
                if(cnOrder.getFirstNum() != null &&cnOrder.getFirstNum()>0){
                    cnOrder.setFlagFirst("1");
                }else{
                    cnOrder.setFlagFirst("0");
                }
                cnOrder.setFlagDurg("1");
                cnOrder.setEuPvtype("3");
                cnOrder.setFlagErase("0");
                //是否嘱托
                cnOrder.setFlagNote("0");
                //是否计费
                cnOrder.setFlagBl("1");
                cnOrder.setFlagSign("1");
                cnOrder.setDateSign(new Date());
                cnOrder.setEuStatusOrd("1");
                cnOrder.setFlagDoctor("1");//医生是1，护士是0
            }
            cnOrderRepository.saveAll(cnOrderList);
            resultListener.success("SUCCESS");
        }catch (Exception e){
            resultListener.exception(e.getMessage());
        }


    }

    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return null;
    }

    @Override
    public List<CnOrder> list(String pkPv) {
        return null;
    }

    /**
     * 保存
     * @param cnOrders
     */
    @Override
    public void save(List<CnOrder> cnOrders) {
        Date date = new Date();
        for (int i = 0; i < cnOrders.size(); i++) {
            CnOrder cnOrder = cnOrders.get(i);
            cnOrder.setEuStatusOrd("0");
            Integer ordsn = 0;
            if(i ==0){
                ordsn = getSerialNo("CN_ORDER", "ORDSN", 1);
                cnOrder.setOrdsn(ordsn);
                cnOrder.setOrdsnParent(ordsn);
            }else{
                cnOrder.setOrdsn(getSerialNo("CN_ORDER", "ORDSN", 1));
                cnOrder.setOrdsnParent(ordsn);
            }
            cnOrder.setTs(date);
            cnOrder.setDateEnter(date);
            cnOrder.setOrdsnChk(cnOrder.getOrdsn());
            cnOrder.setCreateTime(date);
            cnOrder.setDelFlag("0");
            cnOrder.setTs(date);
            BdPd bdPd = bdPdRepository.getOne(cnOrder.getPkOrd());
            BdOrdType bdOrdType = bdOrdTypeRepository.getOne(bdPd.getPkOrdtype());
            cnOrder.setCodeOrdtype(bdOrdType.getCode());
            cnOrder.setCodeOrd(bdPd.getCode());
            cnOrder.setNameOrd(bdPd.getName());
            cnOrder.setSpec(bdPd.getSpec());
            cnOrder.setPkUnitDos(bdPd.getPkUnitMin());
            if(bdPd.getPackSize()!= null&& Double.valueOf(bdPd.getPackSize())!=0 &&
                    "0".equals(cnOrder.getEuAlways())&&
                    !StringUtil.isNullOrEmpty(bdPd.getPkPd())){
                double execTimes = 0;
                if("0".equals(bdPd.getEuMuputype())){//按次取整
                    cnOrder.setQuanCg(Math.floor(Double.valueOf(bdPd.getPackSize())/(Double.valueOf(bdPd.getPackSize()) > 1 ? 1 : Double.valueOf(bdPd.getPackSize())))*execTimes);
                }else{ //按批取整
                    cnOrder.setQuanCg(Math.floor(Double.valueOf(bdPd.getPackSize())/(Double.valueOf(bdPd.getPackSize()) > 1 ? 1 : Double.valueOf(bdPd.getPackSize())))*execTimes);
                }
            }
            cnOrder.setPackSize(Double.valueOf(bdPd.getPackSize()==null?"0":bdPd.getPackSize().toString()));
            cnOrder.setPriceCg(Double.valueOf(bdPd.getPrice()==null?"0":bdPd.getPrice().toString()));
            if(cnOrder.getFirstNum() != null &&cnOrder.getFirstNum()>0){
                cnOrder.setFlagFirst("1");
            }else{
                cnOrder.setFlagFirst("0");
            }
            cnOrder.setFlagDurg("1");
            cnOrder.setEuPvtype("3");
            cnOrder.setFlagErase("0");
            //是否嘱托
            cnOrder.setFlagNote("0");
            //是否计费
            cnOrder.setFlagBl("1");
            cnOrder.setFlagDoctor("1");
            cnOrder.setFlagSign("0");

        }
        cnOrderRepository.saveAll(cnOrders);
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
        String pkDeptExe = "";
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("delFlag"),"0"));
                predicates.add(criteriaBuilder.equal(root.get("dtButype"),"02"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdDeptBu> deptBuList = bdDeptBuRepository.findAll(specification);
        if(deptBuList.size()>0){
            String pkDeptBu = deptBuList.get(0).getPkDeptbu();
            Specification specification1 = new Specification() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get("delFlag"),"0"));
                    predicates.add(criteriaBuilder.equal(root.get("pkDeptbu"),pkDeptBu));
                    predicates.add(criteriaBuilder.equal(root.get("dtDepttype"),"0402"));
                    predicates.add(criteriaBuilder.equal(root.get("flagDef"),"1"));
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            };
            List<BdDeptBus> deptBusList = bdDeptBusRepository.findAll(specification1);
            if(deptBuList.size()>0){
                pkDeptExe = deptBusList.get(0).getPkDept();
            }
        }
        return pkDeptExe;
    }

    @Override
    public List<BdOuDept> getExeDeptList(String deptCode) {
        return null;
    }

    /**
     * 保存医嘱
     * @param object
     * @param listener
     */
    @Override
    public void save(Object object, ResultListener listener) {

    }

    @Override
    public void getOne(String id, ResultListener listener) {

    }
}
