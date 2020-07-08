package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BD_ORDTYPE
 * 医嘱类型定义
 * @author 
 */
@Data
@Entity
@Table(name = "BD_ORDTYPE")
public class BdOrdType implements Serializable {
    /**
     * 医嘱类型主键
     */
    @Id
    private String pkOrdtype;

    /**
     * 所属机构
     */
    private String pkOrg;

    /**
     * 医嘱类型编码
     */
    private String code;

    /**
     * 医嘱类型名称
     */
    private String name;

    /**
     * 上级类型主键
     */
    private String pkParent;

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
    private String flagPrt;

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

    /**
     * 医嘱录入分类
     */
    private String euCpoetype;

    private static final long serialVersionUID = 1L;
}