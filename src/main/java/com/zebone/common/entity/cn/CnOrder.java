package com.zebone.common.entity.cn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * CN_ORDER
 * @author 
 */
@Data
public class CnOrder implements Serializable {
    private String pkCnord;

    private String pkOrg;

    private String euPvtype;

    private String pkPv;

    private String pkPi;

    private Date dateEffe;

    private String codeOrdtype;

    private String euAlways;

    private BigDecimal ordsn;

    private BigDecimal ordsnParent;

    private String pkOrd;

    private String codeOrd;

    private String pkPres;

    private String nameOrd;

    private String descOrd;

    private String codeApply;

    private String codeFreq;

    private String spec;

    private BigDecimal dosage;

    private String pkUnitDos;

    private BigDecimal quan;

    private String pkUnit;

    private String codeSupply;

    private BigDecimal quanCg;

    private String pkUnitCg;

    private BigDecimal packSize;

    private BigDecimal priceCg;

    private String noteSupply;

    private Integer days;

    private Integer dripSpeed;

    private Integer ords;

    private String flagFirst;

    private Integer lastNum;

    private String pkOrgExec;

    private String pkDeptExec;

    private String euStatusOrd;

    private Date dateEnter;

    private Date dateStart;

    private String flagDurg;

    private String flagSelf;

    private String flagNote;

    private String flagBase;

    private String flagBl;

    private String pkDept;

    private String pkDeptNs;

    private String pkWg;

    private String pkEmpInput;

    private String nameEmpInput;

    private String pkEmpOrd;

    private String nameEmpOrd;

    private Date dateSign;

    private String pkEmpChk;

    private String nameEmpChk;

    private Date dateChk;

    private Date dateLastEx;

    private Date datePlanEx;

    private Date dateStop;

    private String pkEmpStop;

    private String nameEmpStop;

    private String flagStop;

    private Date dateStopChk;

    private String pkEmpStopChk;

    private String nameEmpStopChk;

    private String flagStopChk;

    private Date dateErase;

    private String pkEmpErase;

    private String nameEmpErase;

    private String flagErase;

    private Date dateEraseChk;

    private String pkEmpEraseChk;

    private String nameEraseChk;

    private String flagEraseChk;

    private String flagCp;

    private String flagDoctor;

    private Integer infantNo;

    private String pkEvent;

    private String flagPrint;

    private String flagMedout;

    private String euExctype;

    private String pkOrdExc;

    private String flagEmer;

    private String flagThera;

    private String flagPrev;

    private String flagFit;

    private BigDecimal quanBed;

    private String noteOrd;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modityTime;

    private String delFlag;

    private String flagSign;

    private Integer sortIv;

    private Date ts;

    private String flagItc;

    private String euIntern;

    private String euSt;

    private String dtUsagenote;

    private Integer firstNum;

    private String flagPivas;

    private String dtHerbusage;

    private String pkCnordRl;

    private Integer groupno;

    private BigDecimal quanDisp;

    private BigDecimal ratioHp;

    private String descFit;

    private String pkEmpEx;

    private String nameEmpEx;

    private BigDecimal ordsnChk;

    private String codeSupplyAdd;

    private String euOrdtype;

    private static final long serialVersionUID = 1L;
}