package com.zebone.modules.test.controller;

import com.zebone.modules.test.dao.BdOuUserDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试", tags = "测试")
public class BdOuUserController {

    @Autowired
    private BdOuUserDao userDao;

    @ApiOperation(value = "获取操作员信息", notes = "传入id")
    @GetMapping("index")
    public String get(String id){
        return userDao.get(id).toString();
    }
}
