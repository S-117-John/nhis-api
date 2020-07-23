package com.zebone.common.entity.cn;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * CN_LAB_APPLY
 * 临床-医嘱-检查申请
 * @author
 */
@Data
@Entity
@ApiModel(value = "临床-医嘱-检查申请",description = "临床-医嘱-检查申请对应实体类")
public class CnRisApply implements Serializable {

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkOrdris;

    private String pkCnord;

    private String noteDise;

    private String dtRistype;

    private String descBody;

    private String purpose;

    private String pkMsp;

    private Date dateAppo;

    private Date dateExam;

    private String risNotice;

    private Integer ticketno;

    private String euStatus;

    private String flagBed;

    private String note;

    private String pkDiag;

    private String nameDiag;

    private String pkEmpAppo;

    private String nameEmpAppo;

	private String flagPrint;
	
	/*标识医技科室是否打印*/
	private String flagPrint2;

    private String modifier;

    private String flagFasting;

	private String dtPatitrans;

	private Date modityTime;
	
	public String pkOrg;

	/**
     * 创建人
     */
    public String creator;
	/**
     * 创建时间
     */
	public Date createTime;
    

	/**
     * 时间戳
     */
	public Date ts;
	
	
	
	
	
	
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