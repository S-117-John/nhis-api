package com.zebone.modules.mobile.bd.pd.service.impl;

import com.zebone.common.entity.bd.pd.BdPd;
import com.zebone.common.entity.bd.pd.BdPdAs;
import com.zebone.common.entity.bd.unit.BdUnit;
import com.zebone.modules.mobile.bd.pd.dao.BdPdDao;
import com.zebone.modules.mobile.bd.pd.repository.BdPdAsRepository;
import com.zebone.modules.mobile.bd.pd.repository.BdPdRepository;
import com.zebone.modules.mobile.bd.pd.service.BdPdService;
import com.zebone.modules.mobile.bd.pd.vo.BdPdVO;
import com.zebone.modules.mobile.bd.unit.repository.BdUnitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BdPdServiceImpl implements BdPdService {

    @Autowired
    private BdPdDao bdPdDao;

    @Autowired
    private BdPdRepository bdPdRepository;

    @Autowired
    private BdPdAsRepository bdPdAsRepository;

    @Autowired
    private BdUnitRepository bdUnitRepository;

    @Override
    public BdPdVO getBdPd(String pkPd) {
        Optional<BdPd> optionalBdPd = bdPdRepository.findById(pkPd);
        if(!optionalBdPd.isPresent()){
            return null;
        }
        BdPdVO bdPdVO = new BdPdVO();
        BdPd bdPd = optionalBdPd.get();
        BeanUtils.copyProperties(bdPd,bdPdVO);
        String pUnit = bdPd.getPkUnitDef();
        BdUnit bdUnit = bdUnitRepository.getOne(pUnit);
        bdPdVO.setUnitPackName(bdUnit.getName());
        return bdPdVO;
    }

    @Override
    public List<BdPdVO> listBdPs(List<String> ids) {
        //List<BdPd> bdPdList = bdPdRepository.findAllById(ids);
        List<BdPdVO> bdPdList = bdPdDao.getBdPdByPkPd(ids);

        return bdPdList;
    }

    /**
     * 检索药品信息
     * @param spCode
     * @return
     */
    @Override
    public List<BdPdAs> search(String spCode) {

        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<BdPdAs,BdPd> join = root.join("bdPd",JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("flagRm"),"0"));
                predicates.add(criteriaBuilder.equal(join.get("flagReag"),"0"));
                predicates.add(criteriaBuilder.like(root.get("spcode"),"%"+spCode+"%"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<BdPdAs> list = bdPdAsRepository.findAll(specification);
        return list;
    }

    @Override
    public List <Map<String,Object>> getBdPdAndBdOrdByPkPd(List<String> bdPds) {
        List<Map<String,Object>>  bdPdList = bdPdDao.getBdPdAndBdOrdByPkPd(bdPds);
        return bdPdList;
    }


}
