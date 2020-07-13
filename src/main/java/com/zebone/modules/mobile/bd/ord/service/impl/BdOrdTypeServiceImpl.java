package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdTypeRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BdOrdTypeServiceImpl implements BdOrdTypeService {

    @Autowired
    private BdOrdTypeRepository bdOrdTypeRepository;

    @Override
    public List<BdOrdType> listBdOrdType() {
        return bdOrdTypeRepository.findAll();
    }

    @Override
    public BdOrdType getBdOrdType(String id) {
        Optional<BdOrdType> optional = bdOrdTypeRepository.findById(id);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public List<BdOrdType> listRis() {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(root.get("code"),"02%"));
                predicates.add(criteriaBuilder.equal(root.get("delFlag"),"0"));
                predicates.add(criteriaBuilder.notEqual(root.get("code"),"02"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdOrdType> list = bdOrdTypeRepository.findAll(specification);
        return list;
    }
}
