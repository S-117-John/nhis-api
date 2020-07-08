package com.zebone.modules.mobile.bd.ord.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医嘱信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdPdVO {

    private String key;

    /**
     * 名称
     */
    private String name;

    /**
     * 规格
     */
    private String spec;

    /**
     * 包装单位
     */
    private String unit;

    /**
     * 描述
     */
    private String description;

    /**
     * 参考价格
     */
    private Double price;


    /**
     * 库存量
     */
    private Double amount;

    /**
     * 医保类型
     */
    private String medicareType;
}
