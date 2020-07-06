package com.zebone.modules.mobile.cn.vo;

import com.zebone.common.entity.bd.ord.BdOrdLab;
import com.zebone.common.entity.bd.ord.BdOrdRis;
import com.zebone.common.entity.cn.CnOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnOrderVO extends CnOrder {

    private BdOrdRis bdOrdRis;

    private BdOrdLab bdOrdLab;


    /**
     * 页面table需要唯一值
     */
    private String key;

    public String getKey() {
        return getPkCnord();
    }


}
