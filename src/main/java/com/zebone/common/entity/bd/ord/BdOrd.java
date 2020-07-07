package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * BD_ORD
 * @author 
 */
@Data
public class BdOrd implements Serializable {
    private String pkOrd;

    private String pkOrdtype;

    private String codeOrdtype;

    private String code;

    private String name;

    private String namePrt;

    private String spec;

    private String spcode;

    private String dCode;

    /**
     * 0不排 1全排 2组排 3单排
     */
    private String euExclude;

    private String flagNs;

    private String flagDr;

    private String codeFreq;

    private BigDecimal quanDef;

    private String flagIp;

    private String flagOp;

    private String flagEr;

    private String flagHm;

    private String flagPe;

    private String flagEmr;

    private String flagCg;

    /**
     * 用于标识材料
     */
    private String flagPd;

    private String flagActive;

    private String note;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String flagUnit;

    private String pkUnit;

    private String euSex;

    private String flagPed;

    private String dtOrdcate;

    private String oldId;

    private Integer ageMin;

    private Integer ageMax;

    private String itemId;

    private String descOrd;

    private String exceptOrd;

    private String oldType;

    private String ybId;

    private String flagNoc;

    private String codeExt;

    private String euOrdtype;

    private String euArchtype;

    private static final long serialVersionUID = 1L;
}