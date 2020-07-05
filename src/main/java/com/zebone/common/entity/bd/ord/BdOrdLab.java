package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * BD_ORD_LAB
 * @author 
 */
@Data
public class BdOrdLab implements Serializable {
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