package com.zebone.modules.mobile.bd.ord.service.impl;

import com.zebone.common.entity.bd.ord.BdOrdSetDt;
import com.zebone.modules.mobile.bd.ord.dao.BdOrdSetDao;
import com.zebone.modules.mobile.bd.ord.repository.BdOrdSetDtRepository;
import com.zebone.modules.mobile.bd.ord.service.BdOrdSetService;
import com.zebone.modules.mobile.bd.ord.vo.BdOrdSetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class BdOrdSetServiceImpl implements BdOrdSetService {

    @Autowired
    private BdOrdSetDao bdOrdSetDao;

    @Autowired
    private BdOrdSetDtRepository bdOrdSetDtRepository;


    @Override
    public List<BdOrdSetVO> listEmpBdOrdSet(String pkEmp) {
        return bdOrdSetDao.listEmpBdOrdSet(pkEmp);
    }

    @Override
    public List<BdOrdSetDt> listBdOrdSetDt(String pkOrdset) {

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get("delFlag"),"0"));
                predicates.add(criteriaBuilder.equal(root.get("pkOrdset"),pkOrdset));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdOrdSetDt> bdOrdSetDtList = bdOrdSetDtRepository.findAll(specification);
        return bdOrdSetDtList;
    }
}
