package com.zebone.common.entity.bd.pd;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * BD_PD_AS
 * @author 
 */
@Data
@Entity
@Table(name = "BD_PD_AS")
public class BdPdAs implements Serializable {

    @Id
    private String pkPdas;

    private String pkOrg;

    @Transient
//    @Column(name = "PK_PD")
    private String pkPd;

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
    @JoinColumn(name="PK_PD",referencedColumnName = "PK_PD")
//    @JoinTable(name = "BD_PD",joinColumns = {@JoinColumn(name="PK_PD",referencedColumnName = "PK_PD")})
    private BdPd bdPd;

    private static final long serialVersionUID = 1L;
}