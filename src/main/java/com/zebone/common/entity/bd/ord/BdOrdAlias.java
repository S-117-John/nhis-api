package com.zebone.common.entity.bd.ord;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * BD_ORD_ALIAS
 * @author 
 */
@Data
@Entity
@Table(name = "BD_ORD_ALIAS")
public class BdOrdAlias implements Serializable {

    @Id
    private String pkOrdalia;

    private String pkOrg;

    @Transient
//    @Column(name = "PK_ORD")
    private String pkOrd;

    private String alias;

    private String spcode;

    private String dCode;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="PK_ORD",referencedColumnName = "PK_ORD")
    private BdOrd bdOrd;

    private static final long serialVersionUID = 1L;
}