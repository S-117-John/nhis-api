package com.zebone.modules.mobile.application;

import com.zebone.modules.mobile.bd.unit.service.BdUnitService;
import com.zebone.modules.mobile.common.constant.CnOrdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CommonApplicationRunner implements ApplicationRunner {

    @Autowired
    private BdUnitService bdUnitService;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        CnOrdConstant.listBdUnit = bdUnitService.listBdUnit();
    }
}
