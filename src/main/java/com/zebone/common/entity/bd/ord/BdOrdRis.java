package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BD_ORD_RIS
 * 医嘱-检查项目定义
 * @author 
 */
@Data
@Entity
@Table(name = "BD_ORD_RIS")
public class BdOrdRis implements Serializable {
    @Id
    private String pkOrdris;

    private String pkOrg;

    private String pkOrd;

    private String dtType;

    private String dtBody;

    /**
     * 注意事项
     */
    private String descAtt;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modityTime;

    private String delFlag;

    private Date ts;

    private String reportHeader;

    private String reportFooter;

    private static final long serialVersionUID = 1L;
}