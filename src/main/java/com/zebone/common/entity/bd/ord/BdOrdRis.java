package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * BD_ORD_RIS
 * @author 
 */
@Data
public class BdOrdRis implements Serializable {
    private String pkOrdris;

    private String pkOrg;

    private String pkOrd;

    private String dtType;

    private String dtBody;

    /**
     * 注意事项
     */
    private String descAtt;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modityTime;

    private String delFlag;

    private Date ts;

    private String reportHeader;

    private String reportFooter;

    private static final long serialVersionUID = 1L;
}