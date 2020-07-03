package com.zebone.common.entity.pi;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * PI_MASTER
 * @author 
 */
@Data
public class PiMaster implements Serializable {
    private String pkPi;

    private String pkOrg;

    private String codePi;

    private String codeOp;

    private String codeIp;

    private String barcode;

    private String pkPicate;

    private String namePi;

    private String dtIdtype;

    private String idNo;

    private String hicNo;

    private String insurNo;

    private String mpi;

    private String flagEhr;

    private String dtSex;

    private Date birthDate;

    private String placeBirth;

    private String dtMarry;

    private String dtOccu;

    private String dtEdu;

    private String dtCountry;

    private String dtNation;

    private String telNo;

    private String mobile;

    private String wechatNo;

    private String email;

    private String unitWork;

    private String telWork;

    private String address;

    private String nameRel;

    private String telRel;

    private String dtBloodAbo;

    private String dtBloodRh;

    private String creator;

    private Date createTime;

    private String modifier;

    private String delFlag;

    private Date ts;

    private String dtRalation;

    private String addrcodeBirth;

    private String addrBirth;

    private String addrcodeOrigin;

    private String addrOrigin;

    private String addrcodeRegi;

    private String addrRegi;

    private String addrRegiDt;

    private String postcodeRegi;

    private String addrcodeCur;

    private String addrCur;

    private String addrCurDt;

    private String postcodeCur;

    private String postcodeWork;

    private String addrRel;

    private String codeEr;

    private String dtIdtypeRel;

    private String idnoRel;

    private String flagRealname;

    private String pkEmp;

    private String spcaNo;

    private String senNo;

    private String mcno;

    private String citizenNo;

    private String dtSpecunit;

    private String dtSource;

    private String note;

    private Integer cntIp;

    private byte[] photoPi;

    private static final long serialVersionUID = 1L;
}