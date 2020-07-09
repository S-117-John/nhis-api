package com.zebone.common.entity.bd.supply;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_SUPPLY
 * @author 
 */
@Data
@Entity
public class BdSupply implements Serializable {
    /**
     * 用法主键
     */
    @Id
    private String pkSupply;

    /**
     * 所属机构
     */
    private String pkOrg;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 打印名称
     */
    private String namePrint;

    /**
     * 拼音码
     */
    private String spcode;

    /**
     * 自定义码
     */
    private String dCode;

    /**
     * 医嘱打印标志
     */
    private String flagPrint;

    /**
     * 用法分类
     */
    private String pkSupplycate;

    /**
     * 对应执行卡类型：例如 1 护理卡 2 口服卡 3 注射卡 4 输液卡 5 饮食卡 99  其他卡
     */
    private String dtExcardtype;

    /**
     * 配液标志
     */
    private String flagPivas;

    /**
     * 试敏标志
     */
    private String flagSt;

    /**
     * 备注
     */
    private String note;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 删除标志
     */
    private String delFlag;

    private Date ts;

    private Integer sortno;

    private String oldId;

    private String flagAdd;

    private String euRange;

    private static final long serialVersionUID = 1L;
}