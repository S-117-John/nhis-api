package com.zebone.common.entity.cn;



import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
/**
 * CN_ORD_ANTI
 * 临床-医嘱-抗菌药物监测
 * @author
 */
@Data
@Entity
@ApiModel(value = "临床-医嘱-抗菌药物监测",description = "临床-医嘱-抗菌药物监测对应实体类")
public class CnOrdAnti {

	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkOrdanti;

    private String pkOrg;

    private String pkCnord;

	private String euType;

	private String euPrevtype;

	private String euTheratype;

	private String dtOpprevtype;

	private Date dateEntry;

	private String pkEmpEntry;

	private String nameEmpEntry;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modityTime;

    private String delFlag;

    private Date ts;

}
