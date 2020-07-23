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
     * 用法名称
     */
    private String nameSupply;
    
    /**
     * 频次
     */
    private String nameFreq;

    /**
     * 页面table需要唯一值
     */
    private String key;


    public String bdOrdTypeName;

    /**
     * 包装单位
     */
    private String unit;

    /**
     * 参考价格
     */
    private String price;

    /**
     * 医嘱类型编码
     */
    private String codeOrdType;
    /**
     * 是否当前
     */
    private String isnow;

    private String sign;

}
