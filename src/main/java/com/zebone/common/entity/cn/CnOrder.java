package com.zebone.common.entity.cn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    /**
     * 医嘱重复类型
     * 1：临时
     * 0：长期
     */
    private String euAlways;

    private BigDecimal ordsn;

    private BigDecimal ordsnParent;

    private String pkOrd;

    private String codeOrd;

    private String pkPres;

    private String nameOrd;

    private String descOrd;

    private String codeApply;

    /**
     * 频次
     */
    private String codeFreq;

    private String spec;

    private BigDecimal dosage;

    private String pkUnitDos;

    /**
     * 用量
     */
    private BigDecimal quan;

    private String pkUnit;

    /**
     * 用法
     */
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

    /**
     * 末日次数
     */
    private Integer lastNum;

    private String pkOrgExec;

    private String pkDeptExec;

    private String euStatusOrd;

    private Date dateEnter;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    /**
     * 开立医生名称
     */
    private String nameEmpOrd;

    private Date dateSign;

    private String pkEmpChk;

    private String nameEmpChk;

    private Date dateChk;

    private Date dateLastEx;

    private Date datePlanEx;

    /**
     * 停止时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateStop;

    private String pkEmpStop;

    /**
     * 停止医生姓名
     */
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

    /**
     * 首日次数
     */
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