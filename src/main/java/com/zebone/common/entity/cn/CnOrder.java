package com.zebone.common.entity.cn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * CN_ORDER
 * 医嘱
 * @author 
 */
@Data
@Entity
@ApiModel(value = "医嘱信息",description = "医嘱信息表对应实体类")
public class CnOrder implements Serializable {

    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkCnord;

    /**
     * 所属机构
     */
    @ApiModelProperty("所属机构")
    private String pkOrg;

    /**
     * 就诊类型编码
     */
    @ApiModelProperty("就诊类型编码")
    private String euPvtype;

    /**
     * 就诊主键
     */
    @ApiModelProperty("就诊主键")
    private String pkPv;

    /**
     *患者主键
     */
    @ApiModelProperty("患者主键")
    private String pkPi;

    /**
     * 医嘱有效日期
     */
    @ApiModelProperty("医嘱有效日期")
    private Date dateEffe;

    /**
     * 医嘱类型编码
     */
    @ApiModelProperty("医嘱类型编码")
    private String codeOrdtype;

    /**
     * 医嘱重复类型
     * 1：临时
     * 0：长期
     */
    @ApiModelProperty("医嘱重复类型")
    private String euAlways;

    /**
     * 医嘱序号
     */
    @ApiModelProperty("医嘱序号")
    private Integer ordsn;

    /**
     * 父医嘱序号
     */
    @ApiModelProperty("父医嘱序号")
    private Integer ordsnParent;

    /**
     * 医嘱编码主键
     */
    @ApiModelProperty("医嘱编码主键")
    private String pkOrd;

    /**
     * 医嘱编码
     */
    @ApiModelProperty("医嘱编码")
    private String codeOrd;

    /**
     * 处方编码
     */
    @ApiModelProperty("处方编码")
    private String pkPres;

    /**
     * 医嘱名称
     */
    @ApiModelProperty("医嘱名称")
    private String nameOrd;

    /**
     * 医嘱描述
     */
    @ApiModelProperty("医嘱描述")
    private String descOrd;


    private String codeApply;

    /**
     * 频次
     */
    @ApiModelProperty("频次")
    private String codeFreq;

    private String spec;

    /**
     * 剂量
     */
    @ApiModelProperty("剂量")
    private Double dosage;

    /**
     * 剂量单位
     */
    @ApiModelProperty("剂量单位")
    private String pkUnitDos;

    /**
     * 用量
     */
    @ApiModelProperty("用量")
    private Double quan;

    private String pkUnit;

    /**
     * 用法
     */
    @ApiModelProperty("用法")
    private String codeSupply;

    private Double quanCg;

    private String pkUnitCg;

    private Double packSize;

    private Double priceCg;

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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateEnter;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateStart;

    @ApiModelProperty("药品标志")
    private String flagDurg;

    @ApiModelProperty("自备药标志")
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
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateChk;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dateLastEx;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    private String pkOrdanti;

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

    @ApiModelProperty("医嘱备注")
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

    @ApiModelProperty("皮试标志")
    private String euSt;

    private String dtUsagenote;

    /**
     * 首日次数
     */
    @ApiModelProperty("首日次数")
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

    private Integer ordsnChk;

    private String codeSupplyAdd;

    private String euOrdtype;

    //监测类型
    private String euMonitorType;


    private static final long serialVersionUID = 1L;
}