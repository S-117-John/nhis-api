package com.zebone.modules.mobile.cn.vo;

import com.zebone.common.entity.bd.ord.BdOrdLab;
import com.zebone.common.entity.bd.ord.BdOrdRis;
import com.zebone.common.entity.bd.ord.BdOrdType;
import com.zebone.common.entity.cn.CnOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnOrderVO extends CnOrder {

    private BdOrdRis bdOrdRis;

    private BdOrdLab bdOrdLab;

    private BdOrdType bdOrdType;

    /**
     * 页面table需要唯一值
     */
    private String key;


    public String bdOrdTypeName;

}
