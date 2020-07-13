package com.zebone.common.entity.bd.pd;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

/**
 * BD_PD
 * @author 
 */
@ApiModel(value = "药品信息",description = "药品信息表对应实体类")
@Data
@Entity
@Table(name = "BD_PD")
public class BdPd implements Serializable {


    @Id
    @Column(name = "PK_PD")
    private String pkPd;

    private String code;

    private String name;

    private String spec;

    private String shortName;

    private String barcode;

    private String spcode;

    private BigDecimal concent;

    private BigDecimal weight;

    private String pkUnitWt;

    private BigDecimal vol;

    private String pkUnitVol;

    private String pkUnitMin;

    private Integer packSize;

    private String pkUnitPack;

    private String euMuputype;

    private String euPdtype;

    /**
     * 药品类别
     * 0西药，1成药，2草药
     */
    private String euDrugtype;

    private String nameChem;

    private String pkFactory;

    private String apprNo;

    private String euPdprice;

    private String euPap;

    private BigDecimal amtPap;

    private BigDecimal papRate;

    private String dtAbrd;

    private String dtMade;

    private String dtDosage;

    private String dtPharm;

    private String dtPois;

    private String dtAnti;

    private String flagPrecious;

    private String euUsecate;

    private String dtStoretype;

    private String dtBase;

    private String flagRm;

    private String flagReag;

    private String flagVacc;

    private String flagSt;

    private String flagGmp;

    private String flagTpn;

    private String flagPed;

    private String note;

    private BigDecimal dosageDef;

    private String pkUnitDef;

    private String codeSupply;

    private String codeFreq;

    private String dtChcate;

    private String pkItemcate;

    /**
     * 对应医嘱类型
     */
    private String pkOrdtype;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private String pkOrg;

    private BigDecimal price;

    private Integer validCnt;

    private String euValidUnit;

    private String flagStop;

    private String euSource;

    private Date ts;

    private String pkPdind;

    private String pkPdcate;

    private String regNo;

    private Date dateValidReg;

    private String euStockmode;

    private String codeCostitem;

    private String pkItem;

    private String flagSingle;

    private String flagImp;

    private String flagUse;

    private String pkPdgn;

    private String dtPdtype;

    private Integer packSizeMax;

    private String dtUsagenote;

    private String dtInjtype;

    private String flagChrt;

    private String euHerbtype;

    private String oldYbId;

    private String oldId;

    private Integer ageMin;

    private Integer ageMax;

    private String euSex;

    /**
     * 编码重新生成,该字段用于存放旧编码
     */
    private String oldCode;

    private BigDecimal quotaDos;

    private String euHptype;

    private String codeHp;

    private String codeStd;

    private String nameGen;

    private String euLabeltype;

    private String pkAudit;

    private String codeExt;

    private BigDecimal valDdd;

    private String dtDrugeffect;

    private String euMuputypeOp;

    private String remark;


//    @OneToOne(mappedBy = "bdPd")
//    private BdPdAs bdPdAs;

    private static final long serialVersionUID = 1L;
}