package com.zebone.modules.test.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * BD_OU_USER
 * @author 
 */
@Data
public class BdOuUser implements Serializable {
    private String pkUser;

    private String pkOrg;

    private String codeUser;

    private String nameUser;

    private String pkUsrgrp;

    private String pkEmp;

    private String pwd;

    /**
     * 1:是
   0:否
     */
    private String flagActive;

    /**
     * 1:是
   0:否
     */
    private String isLock;

    private String caid;

    private String creator;

    private Date createTime;

    private String nameEmp;

    /**
     * 0静态密码，1CA认证
     */
    private String euCerttype;

    /**
     * 0员工，9系统管理
     */
    private String euUsertype;

    private Date ts;

    private Integer daysValid;

    private Integer cntLock;

    private Date dateLocked;

    private Date datePmd;

    private String spcode;

    private String dCode;

    private static final long serialVersionUID = 1L;
}