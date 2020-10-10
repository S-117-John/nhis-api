package com.zebone.common.entity.bd;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "BD_DEFDOC")
@Data
public class BdDefdoc {

    @Id
    private String pkDefdoc;

    private String pkOrg;

    private String codeDefdoclist;

    private String code;

    private String baCode;

    private String name;

    private String shortname;

    private String pyCode;

    private String dCode;

    private String memo;

    private String creator;

    private Date createTime;

    private String delFlag;

    private String flagDef;

    private Date ts;

    private String codeParent;

    private String pkDefdoclist;

    private String spcode;

    private String modifier;

    private Date modityTime;

    private String euType;

    private String codeStd;

    private String oldId;

    private String nameStd;

    private String attrDesc;

    private String valAttr;

    private String flagSys;

    private static final long serialVersionUID = 1L;
}
