package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 医嘱模板
 * @author 
 */
@Data
public class BdOrdSet implements Serializable {
    private String pkOrdset;

    private String pkOrg;

    private String code;

    private String name;

    /**
     * 0 全院，1 科室，2 医生
     */
    private String euRight;

    private String pkDept;

    private String pkEmp;

    private String pkDiag;

    private String pkParent;

    private String spcode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String euOrdtype;

    private String flagIp;

    private String flagOp;

    private static final long serialVersionUID = 1L;
}