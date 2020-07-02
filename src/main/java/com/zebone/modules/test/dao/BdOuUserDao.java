package com.zebone.modules.test.dao;


import com.zebone.modules.test.entity.BdOuUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BdOuUserDao {

//    @Select("select * from BD_OU_USER where PK_USER = #{id}")
    BdOuUser get(@Param("id") String id);
}