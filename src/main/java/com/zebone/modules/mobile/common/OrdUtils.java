package com.zebone.modules.mobile.common;

import com.zebone.common.entity.bd.serialno.BdSerialno;
import com.zebone.modules.mobile.cn.dao.CnOrderDao;
import com.zebone.modules.mobile.config.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrdUtils {

    public static Integer getSerialNo(String tableName, String fieldName, int count) {
        if(tableName==null) {
            return 0;
        }
        CnOrderDao cnOrderDao = SpringContextHolder.getApplicationContext().getBean("cnOrderDao",CnOrderDao.class);
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
}
