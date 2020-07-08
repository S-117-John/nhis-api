package com.zebone.common.entity.pv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * PV_DIAG
 * 临床综合诊断
 * @author 
 */
@Data
@Entity
public class PvDiag implements Serializable {
    /**
     * 就诊诊断主键
     */
    @Id
    private String pkPvdiag;

    /**
     * 所属机构
     */
    private String pkOrg;

    /**
     * 就诊主键
     */
    private String pkPv;

    /**
     * 诊断序号
     */
    private Integer sortNo;

    /**
     * 诊断类型  0X 门诊过程：00 门诊诊断。
   1X 住院过程：10  入院诊断，11 手术诊断，12 出院诊断。
   2X 医技辅助：20 放射诊断，21 病理诊断
     */
    private String dtDiagtype;

    /**
     * 诊断编码
     */
    private String pkDiag;

    /**
     * 诊断描述
     */
    private String descDiag;

    /**
     * 主诊断标志
     */
    private String flagMaj;

    /**
     * 疑似诊断标志
     */
    private String flagSusp;

    /**
     * 传染病标志
     */
    private String flagContagion;

    /**
     * 诊断日期
     */
    private Date dateDiag;

    /**
     * 诊断医生
     */
    private String pkEmpDiag;

    /**
     * 诊断医生姓名
     */
    private String nameEmpDiag;

    /**
     * 确诊标志
     */
    private String flagFinally;

    /**
     * 治愈标志
     */
    private String flagCure;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 删除标志
     */
    private String delFlag;

    private Date ts;

    private String dtTreatway;

    private BigDecimal val;

    private String codeIcd;

    private String euAdmcon;

    private String pkFather;

    private String nameDiag;

    private String euSptype;

    private String pkDcdt;

    private String codeDcdt;

    private String pkCcdt;

    private String codeCcdt;

    private String descBodypart;

    private String descDrgprop;

    private String flagMajChn;

    private static final long serialVersionUID = 1L;
}