package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.common.entity.bd.ord.BdOrd;
import com.zebone.common.entity.bd.ord.BdOrdAlias;
import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdAliasRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BdOrdServiceImpl implements BdOrdService {

    @Autowired
    private BdOrdAliasRepository bdOrdAliasRepository;

    @Override
    public List<BdOrdAlias> search(String spCode) {
        Specification specification = new Specification() {
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
        List<BdOrdAlias> list = bdOrdAliasRepository.findAll(specification);
        return list;
    }
}
