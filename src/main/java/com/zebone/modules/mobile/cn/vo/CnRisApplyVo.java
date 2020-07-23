package com.zebone.modules.mobile.cn.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zebone.common.entity.cn.CnRisApply;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnRisApplyVo extends CnRisApply {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String euOrdtype; //科研医嘱标志

    public String flagFit;//适应症标志

    
    /**
	 * 数量
	 */
	private double quan ;
	/**
	 * 加急标志
	 */
	private String flagEmer;
	/**
	 * 执行机构
	 */
	private String pkOrgExec;
	/**
	 * 执行科室
	 */
	private String pkDeptExec;
	/**
	 * 医嘱备注
	 */
	private String noteOrd;
	private Integer ordsn;
	
	private Integer ordsnParent;
	
	private Integer infantNo;
	
	private String pkPv;
	/**
	 * 医嘱类型
	 */
	private String codeOrdType;
	
	/**
	 * 医嘱类型编码
	 */
	private String pkOrd;
	/**
	 * 医嘱编码
	 */
	private String codeOrd;
	/**
	 * 医嘱名称
	 */
	private String nameOrd;
	/**
	 * 开立病区
	 */
	private String pkDeptNs;
	/**
	 * 计费标志
	 */
	private String flagBl;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateStart;
	/**
	 * 申请单号
	 */
	private String codeApply;
	/**
	 * 患者主键
	 */
	private String pkPi;
	private String pkEmpInput;
    
    private String NameEmpInput;
    
    private String pkDept;
    private String priceCg;
    private String euIntern;
    private Integer groupno;
}
