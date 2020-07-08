package com.zebone.common.entity.pi;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * PI_CATE
 * 患者分类
 * @author 
 */
@Data
@Entity
public class PiCate implements Serializable {

    @Id
    private String pkPicate;

    private String pkOrg;

    private String code;

    private String name;

    private String spcode;

    private String dCode;

    private String creator;

    private String note;

    private Date createTime;

    private String modifier;

    private String delFlag;

    /**
     * 优惠类型
     */
    private String pkHp;

    private Date ts;

    private String flagDef;

    private String flagSpec;

    private static final long serialVersionUID = 1L;
}