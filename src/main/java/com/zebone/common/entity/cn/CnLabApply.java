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
 * 临床-医嘱-检验申请
 * @author
 */
@Data
@Entity
@ApiModel(value = "临床-医嘱-检验申请",description = "临床-医嘱-检验申请对应实体类")
public class CnLabApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5158916659361545445L;

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkOrdlis;

    private String pkCnord;

    private String descDiag;

    private String purpose;

    private String dtSamptype;

    private String dtTubetype;

    private String dtColtype;

    private String sampNo;

    private String pkDeptCol;

    private String euStatus;

    private Date dateCol;

    private Date dateAcpt;

    private Date dateRpt;

    private String note;

    private String flagPrt;

    private String modifier;

    private Date modityTime;

    private String pkEmpCol;


    private String nameEmpCol;

    private String formApp;
    

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
        *删除标志  
        */
   	public String delFlag = "0";  // 0未删除  1：删除
   	
}