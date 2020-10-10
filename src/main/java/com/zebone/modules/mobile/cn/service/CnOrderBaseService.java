package com.zebone.modules.mobile.cn.service;

import com.google.common.collect.Lists;
import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdAlias;
import com.zebone.common.entity.bd.ord.BdOrdDept;
import com.zebone.common.entity.bd.ou.BdOuDept;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdAliasRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdDeptRepository;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ou.repository.BdOuDeptRepository;
import com.zebone.modules.mobile.bd.pd.repository.BdPdAsRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Component
public abstract class CnOrderBaseService {

    @Autowired
    private CnOrderDao cnOrderDao;

    @Autowired
    private BdPdAsRepository bdPdAsRepository;

    @Autowired
    private BdOrdAliasRepository bdOrdAliasRepository;

    @Autowired
    private BdOrdTypeRepository bdOrdTypeRepository;

    @Autowired
    private BdOrdDeptRepository bdOrdDeptRepository;

    @Autowired
    private BdOuDeptRepository bdOuDeptRepository;

    @Autowired
    private CnOrderRepository cnOrderRepository;

    /**
     * 获取医嘱序号
     * @param tableName
     * @param fieldName
     * @param count
     * @return
     */
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

    /**
     * 检索医嘱
     * @param spCode
     * @return
     */
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
        Pageable pageable = PageRequest.of(0,50);
        Page<BdOrdAlias> aliasPage = bdOrdAliasRepository.findAll(specificationOrd,pageable);
        List<BdOrdAlias> listOrd = aliasPage.toList();

        //3、创建视图类
        List<CnOrderVO> cnOrderVOList = Lists.newArrayList();
        //4、克隆药品属性
        listPd.forEach(bdPdAs -> {
            CnOrderVO cnOrderVO = new CnOrderVO();
            cnOrderVO.setNameOrd(bdPdAs.getBdPd().getName());
            cnOrderVO.setKey(bdPdAs.getBdPd().getPkPd());
            cnOrderVO.setFlagDurg("1");
            //规格
            cnOrderVO.setSpec(bdPdAs.getBdPd().getSpec());
            cnOrderVO.setPkUnit(bdPdAs.getBdPd().getPkUnitPack());
            //价格
            cnOrderVO.setPrice(bdPdAs.getBdPd().getPrice().stripTrailingZeros().toString());
            cnOrderVO.setCodeOrdType(bdOrdTypeRepository.findById(bdPdAs.getBdPd().getPkOrdtype()).get().getCode());
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
            cnOrderVO.setPkOrd(bdOrdAlias.getBdOrd().getPkOrd());
            cnOrderVOList.add(cnOrderVO);
        });


        //6、属性去重复
        List<CnOrderVO> result = cnOrderVOList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(n->((CnOrderVO) n).getKey()))),ArrayList::new)
        );

        //7、查询执行科室
        result.forEach(cnOrderVO -> {
            List<BdOrdDept> bdOrdDepts = bdOrdDeptRepository.findByPkOrd(cnOrderVO.getPkOrd());
            List<String> pkDepts = Lists.newArrayList();
            bdOrdDepts.forEach(bdOrdDept -> {
                pkDepts.add(bdOrdDept.getPkDept());
            });
            List<BdOuDept> bdOuDeptList = bdOuDeptRepository.findAllById(pkDepts);
            cnOrderVO.setDeptList(bdOuDeptList);
        });

        return result;
    }


    /**
     * 查询医嘱详情
     * @param pkCnord
     * @return
     */
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
     * 检查是否可以停嘱
     * @param pkCnord
     * @return
     */
    public Integer checkStopOrd(String pkCnord){
        return cnOrderDao.checkStopOrd(pkCnord);
    }
}
