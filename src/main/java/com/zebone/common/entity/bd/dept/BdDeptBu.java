package com.zebone.common.entity.bd.dept;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_DEPT_BU
 * @author 
 */
@Data
@Entity
public class BdDeptBu implements Serializable {
    @Id
    private String pkDeptbu;

    private String pkOrg;

    private String code;

    private String name;

    private String dtButype;

    private String note;

    private String spcode;

    private String dCode;

    private String flagSys;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private static final long serialVersionUID = 1L;
}