package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_ORD_SET_DT
 * @author 
 */
@Data
@Entity
public class BdOrdSetDt implements Serializable {

    @Id
    private String pkOrdsetdt;

    private String pkOrdset;

    private String pkOrd;

    private String flagPd;

    private Integer orderNo;

    private Integer parentNo;

    private String codeFreq;

    private String codeSupply;

    private BigDecimal dosage;

    private String pkUnitDos;

    private String supplyNote;

    private BigDecimal quan;

    private String pkUnit;

    private Integer days;

    private String pkDeptExec;

    private String flagDef;

    private String nameOrd;

    private String note;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Integer sortNo;

    private String pkOrgExec;

    private Date ts;

    private String flagSelf;

    private String dtHerbusage;

    private static final long serialVersionUID = 1L;
}