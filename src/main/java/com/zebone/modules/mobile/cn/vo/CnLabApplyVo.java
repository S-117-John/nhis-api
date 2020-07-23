package com.zebone.modules.mobile.cn.vo;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnLabApplyVo extends CnLabApply {

    public String euOrdtype; //科研医嘱标志

    public String flagFit;//适应症标志


    /**
	 * 申请单号
	 */
	private String codeApply;
	/**
	 * 就诊id
	 */
	private String pkPv;
	/**
	 * 患者主键
	 */
	private String pkPi;
	/**
	 * 执行机构
	 */
	private String pkOrgExec;
	/**
	 * 执行科室
	 */
	private String pkDeptExec;
	/**
	 * 医嘱状态
	 * @return
	 */
	private String euStatusOrd;
	/**
	 * 数量
	 */
	private double quan ;
	/**
	 * 加急标志
	 */
	private String flagEmer;
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
	 * 开立科室
	 */
	private String pkDept;
	/**
	 * 开立病区
	 */
	private String pkDeptNs;
	/**
	 * 医嘱备注
	 */
	private String noteOrd;
	/**
	 * 计费标志
	 */
	private String flagBl;
	/**
	 * 开立人
	 */
	private String nameEmpOrd;

	/**
	 * 医嘱类型
	 */
	private String codeOrdType;
	
	/**
	 * 婴儿序号
	 */
    private Integer infantNo;
    
   	private Integer ordsn;
   	
   	private Integer ordsnParent;
   	
   	private Integer groupno;
   	
   	private Date dateStart;
   	
   	private String pkEmpInput;
    
    private String NameEmpInput;
    
    private String priceCg;
}
