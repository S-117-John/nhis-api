package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BD_ORD_LAB
 * @author 
 */
@Data
@Entity
@Table(name = "BD_ORD_LAB")
public class BdOrdLab implements Serializable {
    @Id
    private String pkOrdlab;

    private String pkOrg;

    private String pkOrd;

    private String dtColltype;

    private String dtLisgroup;

    private String dtContype;

    private String note;

    private String dtSamptype;

    private String spcode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String durationRpt;

    private static final long serialVersionUID = 1L;
}