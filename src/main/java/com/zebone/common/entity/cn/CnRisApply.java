package com.zebone.common.entity.cn;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	
	
	
	
	
	

}