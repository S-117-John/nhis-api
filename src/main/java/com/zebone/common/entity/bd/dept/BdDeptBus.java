package com.zebone.common.entity.bd.dept;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_DEPT_BUS
 * @author 
 */
@Data
@Entity
public class BdDeptBus implements Serializable {

    @Id
    private String pkDeptbus;

    private String pkOrg;

    private String pkDeptbu;

    private String pkDept;

    private Integer sort;

    /**
     * 010200
     */
    private String dtDepttype;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String flagDef;

    private String timeBegin;

    private String timeEnd;

    private static final long serialVersionUID = 1L;
}