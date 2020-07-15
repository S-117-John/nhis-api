package com.zebone.modules.mobile.cn.service.impl;


import com.google.common.collect.Lists;
import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdAlias;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.common.entity.cn.CnOrder;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdAliasRepository;
import com.zebone.modules.mobile.bd.pd.repository.BdPdAsRepository;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.cn.repository.CnOrderRepository;
import com.zebone.modules.mobile.cn.service.CnOrdService;
import com.zebone.modules.mobile.cn.vo.CnOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingLong;
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
                predicates.add(criteriaBuilder.equal(join.get("flagActive"),"0"));
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
                        toCollection(() -> new TreeSet<>(comparing(n->n.getKey()))),ArrayList::new)
        );

        return result;
    }
}
