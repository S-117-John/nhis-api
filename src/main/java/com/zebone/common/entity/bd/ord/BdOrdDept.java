package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BD_ORD_DEPT
 * @author 
 */
@Data
@Entity
@Table(name = "BD_ORD_DEPT")
public class BdOrdDept implements Serializable {

    @Id
    private String pkOrddept;

    private String pkOrg;

    private String pkOrdorg;

    private String pkOrd;

    private String pkOrgExec;

    /**
     * 没有执行科室定义代表开立科室病区执行
     */
    private String pkDept;

    private String flagDef;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String placeExec;

    private static final long serialVersionUID = 1L;
}