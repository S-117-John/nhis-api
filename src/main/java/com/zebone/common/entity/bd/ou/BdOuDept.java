package com.zebone.common.entity.bd.ou;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BD_OU_DEPT
 * @author 
 */
@Data
@Entity
@Table(name = "BD_OU_DEPT")
public class BdOuDept implements Serializable {

    @Id
    private String pkDept;

    private String pkOrg;

    private String codeDept;

    private String nameDept;

    private String pkFather;

    /**
     * 1：一级 2：二级：3：三级：4：四级
     */
    private String euLevel;

    /**
     * N:普通部门  V:虚拟部门
     */
    private String deptType;

    private String shortname;

    /**
     * 0:停用 1:启用
     */
    private String flagActive;

    /**
     * 来自码表
     */
    private String dtDepttype;

    private Integer bednum;

    private Integer bednumOpen;

    private String namePlace;

    private Integer sortno;

    private String note;

    private String pyCode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String delFlag;

    private String deptDesc;

    /**
     * 门诊使用
     */
    private String flagOp;

    /**
     * 急诊使用
     */
    private String flagEr;

    /**
     * 住院使用
     */
    private String flagIp;

    /**
     * 体检使用
     */
    private String flagPe;

    /**
     * 家床使用
     */
    private String flagHm;

    private Date ts;

    private String telnoDept;

    /**
     * 标准部门编码
     */
    private String dtStdepttype;

    private String dtAcctdept;

    private String pkOrgarea;

    private String oldId;

    private String idMaster;

    private Long wardId;

    private Long isvirtual;

    private String codeOrig;

    private String oldCode;

    @Column(name = "OLD_ID_H")
    private String oldIdH;

    private String dtMedicaltype;

    private String modifier;

    private Date modityTime;

    private static final long serialVersionUID = 1L;
}