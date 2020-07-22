package com.zebone.modules.mobile.cn.service.impl;


import com.google.common.collect.Lists;
import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdAlias;
import com.zebone.common.entity.bd.ou.BdOuUser;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrdAnti;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.common.entity.cn.CnRisApply;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdAliasRepository;
import com.zebone.modules.mobile.bd.pd.repository.BdPdAsRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.repository.CnLabApplyRepository;
import com.zebone.modules.mobile.cn.repository.CnOrdAntiRepository;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.repository.CnRisApplyRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnLabApplyVo;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import com.zebone.modules.mobile.cn.vo.CnRisApplyVo;
import com.zebone.modules.mobile.patient.vo.PvEncounterVO;

import io.netty.util.internal.StringUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class CnOrdServiceImpl implements CnOrdService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Autowired
    private BdPdAsRepository bdPdAsRepository;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    @Autowired
    private CnLabApplyRepository cnLabApplyRepository;

    @Autowired
    private CnOrdAntiRepository cnOrdAntiRepository;

    @Autowired
    private CnRisApplyRepository cnRisApplyRepository;

    @Autowired
    private BdOrdAliasRepository bdOrdAliasRepository;

    /**
     * 查询已开立医嘱
     * @param pkPv
     * @return
     */
    @Override
    public List<CnOrderVO> listPatientOrder(String pkPv) {
        return cnOrderDao.listPatientOrder(pkPv);
    }

    /**
     * 保存医嘱
     * @param cnOrders
     */
    @Override
    public void save(List<CnOrder> cnOrders) {
        cnOrders.forEach(cnOrder -> {
            cnOrder.setCreateTime(new Date());
            cnOrder.setFlagErase("0");
        });
        cnOrderRepository.saveAll(cnOrders);
    }

    /**
     * 医嘱详情
     * @param pkCnord
     * @return
     */
    @Override
    public CnOrderVO getCnOrderDetail(String pkCnord) {
        Optional<CnOrder> optional = cnOrderRepository.findById(pkCnord);
        if(!optional.isPresent()){
            return null;
        }
        CnOrder cnOrder = optional.get();
        CnOrderVO cnOrderVO = new CnOrderVO();
        BeanUtils.copyProperties(cnOrder,cnOrderVO);

        return cnOrderVO;
    }

    /**
     * 检索医嘱
     * @param spCode
     * @return
     */
    @Override
    public List<CnOrderVO> search(String spCode) {
        //1、查询药品
        Specification specificationPd = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<BdPdAs, BdPd> join = root.join("bdPd",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("flagRm"),"0"));
                predicates.add(criteriaBuilder.equal(join.get("flagReag"),"0"));
                predicates.add(criteriaBuilder.like(root.get("spcode"),"%"+spCode+"%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdPdAs> listPd = bdPdAsRepository.findAll(specificationPd);
        //2、查询非药品
        Specification specificationOrd = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<BdOrdAlias, BdOrd> join = root.join("bdOrd",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("delFlag"),"0"));
                predicates.add(criteriaBuilder.equal(join.get("flagActive"),"1"));
                predicates.add(criteriaBuilder.equal(join.get("flagIp"),"1"));
                predicates.add(criteriaBuilder.like(root.get("spcode"),"%"+spCode+"%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdOrdAlias> listOrd = bdOrdAliasRepository.findAll(specificationOrd);

        //3、创建视图类
        List<CnOrderVO> cnOrderVOList = Lists.newArrayList();
        //4、克隆药品属性
        listPd.forEach(bdPdAs -> {
            CnOrderVO cnOrderVO = new CnOrderVO();
            cnOrderVO.setNameOrd(bdPdAs.getAlias());
            cnOrderVO.setKey(bdPdAs.getBdPd().getPkPd());
            cnOrderVO.setFlagDurg("1");
            //规格
            cnOrderVO.setSpec(bdPdAs.getBdPd().getSpec());
            cnOrderVO.setPkUnit(bdPdAs.getBdPd().getPkUnitPack());
            //价格
            cnOrderVO.setPrice(bdPdAs.getBdPd().getPrice().stripTrailingZeros().toString());
            cnOrderVOList.add(cnOrderVO);
        });
        //5、克隆非药品属性
        listOrd.forEach(bdOrdAlias -> {
            CnOrderVO cnOrderVO = new CnOrderVO();
            cnOrderVO.setNameOrd(bdOrdAlias.getAlias());
            cnOrderVO.setKey(bdOrdAlias.getBdOrd().getPkOrd());
            cnOrderVO.setSpec(bdOrdAlias.getBdOrd().getSpec());
            cnOrderVO.setPkUnit(bdOrdAlias.getBdOrd().getPkUnit());
            cnOrderVO.setCodeOrdType(bdOrdAlias.getBdOrd().getCodeOrdtype());
            cnOrderVOList.add(cnOrderVO);
        });


        //6、属性去重复
        List<CnOrderVO> result = cnOrderVOList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(n->((CnOrderVO) n).getKey()))),ArrayList::new)
        );

        return result;
    }
    /**
     * 检查是否可以停嘱
     * @param pkCnord
     * @return
     */
    @Override
    public Integer checkStopOrd(String pkCnord){
    	return cnOrderDao.checkStopOrd(pkCnord);
    }
    /**
     * 查询停嘱列表
     * @param pkCnord
     * @return
     */
	@Override
	public List<CnOrderVO> queryOrdStopListByPk(String pkCnord) {
		// TODO Auto-generated method stub
		return cnOrderDao.queryOrdStopListByPk(pkCnord);
	}
	/**
	 * 查询医嘱是否可以签署
	 * @param pkCnord
	 * @return
	 */
	@Override
	public Integer checkSignOrd(String pkCnord) {
		// TODO Auto-generated method stub
		return cnOrderDao.checkSignOrd(pkCnord);
	}

	@Override
	public Integer signOrd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return cnOrderDao.signOrd(param);
	}

	@Override
	public Integer checkDelOrd(String pkCnord) {
		// TODO Auto-generated method stub
		return cnOrderDao.checkDelOrd(pkCnord);
	}

	@Override
	public Integer delOrd(String pkCnord) {
		// TODO Auto-generated method stub
		return cnOrderDao.delOrd(pkCnord);
	}

	@Override
    public  Integer getSerialNo(String tableName, String fieldName, int count){
        if(tableName==null) return 0;
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
        if(rs==1)
            ret = cnOrderDao.selectSn(tableName.toUpperCase(), fieldName.toUpperCase()).intValue()-count;
        return ret;
    }

   /* *//**
     * 保存检验
     * @param cnRisApply
     *//*
    @Override
    public void saveCnRisApply(List<CnRisApply> cnRisApply){
        cnRisApplyRepository.saveAll(cnRisApply);
    }

    @Override
    public  void saveCnLabApply(List<CnLabApply> cnLabApply){
        cnLabApplyRepository.saveAll(cnLabApply);
    }*/

    @Override
    public  List<CnOrdAnti> saveOrdAnti(List<CnOrder> ordList,String pkOrg){
        if(ordList==null||ordList.size()<=0) {
            return null;
        }
        List<CnOrdAnti> cnOrdAntis = new ArrayList<CnOrdAnti>();
        Date ts = new Date();
        List<String> pks = new ArrayList<String>();
        for(CnOrder ord:ordList){
            if(!StringUtil.isNullOrEmpty(ord.getPkOrdanti()) && ("1".equals(ord.getFlagPrev()) || "1".equals(ord.getFlagThera()))){
                if(!StringUtil.isNullOrEmpty(ord.getPkCnord())){
                    pks.add(ord.getPkCnord());
                }
                CnOrdAnti anti = new CnOrdAnti();
                BeanUtils.copyProperties(anti, ord);
                anti.setEuType(ord.getEuMonitorType());
                anti.setPkOrg(pkOrg);
                anti.setTs(ts);
                cnOrdAntis.add(anti);
            }
        }
        if(cnOrdAntis!=null && cnOrdAntis.size()>0){
            cnOrdAntiRepository.saveAll(cnOrdAntis);
        }
        return cnOrdAntis;
    }
    /**
     * 保存检验申请
     */
	@Override
	public List<CnOrder> saveLisApplyList(BdOuUser user,List<CnLabApplyVo> saveLisList, PvEncounterVO pvEncounterVO) {
		List<CnOrder> signCnOrder = new ArrayList<CnOrder>();
		List<CnOrder> newOrdList = new ArrayList<CnOrder>();
		List<CnLabApply> newLisList = new ArrayList<CnLabApply>();
		Date d = new Date();
		for (CnLabApplyVo lisVO1 : saveLisList) {
			CnOrder orderVO = new CnOrder();
			CnLabApply lisVO = new CnLabApply();
			BeanUtils.copyProperties(lisVO, lisVO1);
			lisVO.setPkOrdlis(UUID.randomUUID().toString().replaceAll("-", ""));
			orderVO.setPkCnord(UUID.randomUUID().toString().replaceAll("-", ""));
			lisVO.setPkCnord(orderVO.getPkCnord());
			orderVO.setInfantNo(lisVO.getInfantNo());
			lisVO.setPkPi(pvEncounterVO.getPkPi());
			lisVO.setPkPv(pvEncounterVO.getPkPv());
			orderVO.setPkPv(lisVO.getPkPv());
			orderVO.setCodeOrdtype(lisVO.getCodeOrdType());
			orderVO.setPkOrd(lisVO.getPkOrd());
			orderVO.setCodeOrd(lisVO.getCodeOrd());
			orderVO.setNameOrd(lisVO.getNameOrd());
			lisVO.setPkDeptNs(pvEncounterVO.getPkDeptNs());
			orderVO.setPkDeptNs(lisVO.getPkDeptNs());//开立病区
			orderVO.setFlagBl(lisVO.getFlagBl());
			orderVO.setEuOrdtype(lisVO1.getEuOrdtype()); // 科研医嘱标志
			orderVO.setFlagFit(lisVO1.getFlagFit());// 适应症标志
			lisVO.setPkOrg(user.getPkOrg());
			lisVO.setEuStatus("0");
			lisVO.setTs(d);
			orderVO.setTs(d);
			orderVO.setPkOrg(lisVO.getPkOrg());
			orderVO.setCodeApply(lisVO.getCodeApply());
			orderVO.setPkPi(lisVO.getPkPi());
			
			lisVO.setOrdsn(this.getSerialNo("CN_ORDER", "ORDSN", 1));
			lisVO.setOrdsnParent(lisVO.getOrdsn());
			orderVO.setOrdsn(lisVO.getOrdsn());
			orderVO.setOrdsnParent(lisVO.getOrdsnParent());
			orderVO.setGroupno(lisVO.getGroupno());
			orderVO.setDateStart(lisVO.getDateStart());
			if (null == lisVO.getPkEmpInput()|| "".equals(lisVO.getPkEmpInput())) {
				orderVO.setPkEmpInput(user.getPkEmp());
				orderVO.setNameEmpInput(user.getNameEmp());
			} else {
				orderVO.setPkEmpInput(lisVO.getPkEmpInput());
				orderVO.setNameEmpInput(lisVO.getNameEmpInput());
			}
			setOrdList(user, orderVO);
			setLisOrdPubField(lisVO, orderVO);

			if (null != lisVO.getPkDept() && !"".equals(lisVO.getPkDept())) {
				orderVO.setPkDept(lisVO.getPkDept());
			}
			orderVO.setPriceCg(null == lisVO.getPriceCg() ? 0 : Double.parseDouble(lisVO.getPriceCg()));
			newLisList.add(lisVO);
			newOrdList.add(orderVO);
			signCnOrder.add(orderVO);
		}
		if (newOrdList.size() > 0) {
			this.save(newOrdList);
		}
		if (newLisList.size() > 0) {
			cnLabApplyRepository.saveAll(newLisList);
		}
		return signCnOrder;
	}
	private void setOrdList(BdOuUser u, CnOrder orderVO) {
		if((orderVO.getOrdsn()==null)  ||(orderVO.getOrdsn()!=null && orderVO.getOrdsn()<=0))
		{
			orderVO.setOrdsn(this.getSerialNo("CN_ORDER", "ORDSN", 1));
			orderVO.setOrdsnParent(orderVO.getOrdsn());
		}	
		orderVO.setDescOrd(orderVO.getNameOrd());	
		orderVO.setEuAlways("1");
		//orderVO.setCodeFreq(ApplicationUtils.getSysparam("CN0019", false));//默认频次
		orderVO.setEuStatusOrd("0");		//保存
		String sfalse = "0";
		orderVO.setFlagDurg(sfalse);
		orderVO.setFlagFirst(sfalse);
		orderVO.setFlagSign(sfalse);
		orderVO.setFlagSelf(sfalse);
		orderVO.setFlagNote(sfalse);
		orderVO.setFlagBase(sfalse);
		orderVO.setFlagStop(sfalse);
		orderVO.setFlagStopChk(sfalse);
		orderVO.setFlagErase(sfalse);
		orderVO.setFlagEraseChk(sfalse);
		orderVO.setFlagCp(sfalse);
	    orderVO.setFlagDoctor("1");
	    orderVO.setFlagPrint(sfalse);
	    orderVO.setFlagMedout(sfalse);
	    orderVO.setFlagEmer(sfalse);
	    orderVO.setFlagThera(sfalse);
	    orderVO.setFlagPrev(sfalse);
	    orderVO.setDelFlag(sfalse);
		orderVO.setEuPvtype("3");
		orderVO.setDateEnter(orderVO.getTs());
		//orderVO.setPkDept(user.);
	}
    private void setLisOrdPubField(CnLabApply lisVO, CnOrder orderVO) {
		orderVO.setQuan(lisVO.getQuan());
		orderVO.setDosage(lisVO.getQuan());
		orderVO.setFlagEmer(lisVO.getFlagEmer());
		orderVO.setPkOrgExec(lisVO.getPkOrgExec());
		orderVO.setPkDeptExec(lisVO.getPkDeptExec());
		orderVO.setNoteOrd(lisVO.getNoteOrd());
	}
    private void setRisOrdPubField(CnRisApply risVO, CnOrder orderVO) {
		orderVO.setQuan(risVO.getQuan());
		orderVO.setDosage(risVO.getQuan());
		orderVO.setFlagEmer(risVO.getFlagEmer());
		orderVO.setPkOrgExec(risVO.getPkOrgExec());
		orderVO.setPkDeptExec(risVO.getPkDeptExec());
		orderVO.setNoteOrd(risVO.getNoteOrd());
		orderVO.setOrdsnChk(orderVO.getOrdsn());
	}
    /**
     * 保存检查申请
     */
	@Override
	public List<CnOrder> saveRisApplyList(BdOuUser user,List<CnRisApplyVo> saveRisList,PvEncounterVO pvEncounterVO) {
		// TODO Auto-generated method stub
		List<CnOrder> signCnOrder = new ArrayList<CnOrder>();
		List<CnOrder> newOrdList = new ArrayList<CnOrder>();
		List<CnRisApply> newRisList = new ArrayList<CnRisApply>();
		Date d = new Date();
		for (CnRisApplyVo risVO1 : saveRisList) {
			CnRisApply risVO = new CnRisApply();
			BeanUtils.copyProperties(risVO, risVO1);
			CnOrder orderVO = new CnOrder();
			risVO.setPkOrdris(UUID.randomUUID().toString().replaceAll("-", ""));
			orderVO.setPkCnord(UUID.randomUUID().toString().replaceAll("-", ""));
			orderVO.setOrdsn(risVO.getOrdsn());
			orderVO.setOrdsnParent(risVO.getOrdsnParent());
			risVO.setPkCnord(orderVO.getPkCnord());
			orderVO.setInfantNo(risVO.getInfantNo());
			risVO.setPkPi(pvEncounterVO.getPkPi());
			risVO.setPkPv(pvEncounterVO.getPkPv());
			orderVO.setPkPv(risVO.getPkPv());
			orderVO.setCodeOrdtype(risVO.getCodeOrdType());
			orderVO.setPkOrd(risVO.getPkOrd());
			orderVO.setCodeOrd(risVO.getCodeOrd());
			orderVO.setNameOrd(risVO.getNameOrd());
			risVO.setPkDeptNs(pvEncounterVO.getPkDeptNs());
			orderVO.setPkDeptNs(risVO.getPkDeptNs());
			orderVO.setFlagBl(risVO.getFlagBl());
			orderVO.setFlagErase("0");
			risVO.setPkOrg(user.getPkOrg());
			risVO.setEuStatus("0");
			risVO.setTs(d);
			orderVO.setTs(d);
			orderVO.setDateStart(risVO.getDateStart());
			risVO.setPkOrg(user.getPkOrg());
			orderVO.setPkOrg(risVO.getPkOrg());
			orderVO.setCodeApply(risVO.getCodeApply());
			orderVO.setPkPi(risVO.getPkPi());
			orderVO.setEuOrdtype(risVO1.getEuOrdtype()); // 科研医嘱标志
			orderVO.setFlagFit(risVO1.getFlagFit());// 适应症标志
			if (null==risVO.getPkEmpInput() || "".equals(risVO.getPkEmpInput())) {
				orderVO.setPkEmpInput(user.getPkEmp());
				orderVO.setNameEmpInput(user.getNameEmp());
			} else {
				orderVO.setPkEmpInput(risVO.getPkEmpInput());
				orderVO.setNameEmpInput(risVO.getNameEmpInput());
			}

			setOrdList(user, orderVO);
			setRisOrdPubField(risVO, orderVO);
			if (null!=risVO.getPkDept() && !"".equals(risVO.getPkDept())) {
				orderVO.setPkDept(risVO.getPkDept());
			}
			//orderVO.setCnSignCa(risVO.getCnSignCa());
			orderVO.setPriceCg(null==risVO.getPriceCg() ? 0
					: Double.parseDouble(risVO.getPriceCg()));
			orderVO.setEuIntern(risVO.getEuIntern());
			orderVO.setGroupno(risVO.getGroupno());
			newRisList.add(risVO);
			newOrdList.add(orderVO);
			signCnOrder.add(orderVO);
		}
		if(newOrdList.size()>0){
			this.save(newOrdList);
		}
		if (newRisList.size() > 0) {
			cnRisApplyRepository.saveAll(newRisList);
		}
		return signCnOrder;
	}
}
