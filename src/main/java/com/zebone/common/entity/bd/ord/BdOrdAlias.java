package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * BD_ORD_ALIAS
 * @author 
 */
@Data
public class BdOrdAlias implements Serializable {
    private String pkOrdalia;

    private String pkOrg;

    private String pkOrd;

    private String alias;

    private String spcode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private static final long serialVersionUID = 1L;
}