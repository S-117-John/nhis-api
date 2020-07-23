package com.zebone.common.entity.bd.encoderule;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;




/**
 * Table: BD_ENCODERULE - bd_encoderule 
 *
 * @since 2020-07-21 01:52:04
 */
@Data
@Entity
@ApiModel(value = "编码规则",description = "编码规则对应实体类")
public class BdEncoderule  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkEncoderule;

    private String code;

    private String name;

    private Integer lengthCode;

    private String flagPrefix;

    private String prefix;

    private String flagSuffix;

    private String suffix;

    private String flagDate;

    private String formatDate;

    /** EU_SNRULE - 0自然增长，1按日归零，2，按月归零，3按年归零 */
    private String euSnrule;

    private Long valInit;

    private Long val;

    private String note;

    /** FLAG_SYS - 为true表示系统内置，不可修改 */
    private String flagSys;

    private Date ts;
    
    private String pkOrg;
   
}