package com.zebone.modules.mobile.bd.ou.repository;

import com.zebone.common.entity.bd.ou.BdOuUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BdOuUserRepository extends JpaRepository<BdOuUser,String> {

    BdOuUser findByCodeUser(String code);
}
