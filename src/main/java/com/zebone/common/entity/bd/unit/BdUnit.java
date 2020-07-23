package com.zebone.common.entity.bd.unit;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_UNIT
 * @author 
 */
@Data
@Entity
public class BdUnit implements Serializable {
    @Id
    private String pkUnit;

    private String pkOrg;

    private String code;

    private String name;

    private String spcode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private static final long serialVersionUID = 1L;
}