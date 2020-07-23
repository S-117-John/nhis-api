package com.zebone.modules.mobile.cn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BdSerialno {
    @Id
    @GeneratedValue(generator="system_uuid")
    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String pkSerialno;

    /**
     * null (NHIS.BD_SERIALNO.PK_ORG)
     *
     */
    private String pkOrg;

    /**
     * null (NHIS.BD_SERIALNO.NAME_TB)
     *
     */
    private String nameTb;

    /**
     * null (NHIS.BD_SERIALNO.NAME_FD)
     *
     */
    private String nameFd;

    /**
     * null (NHIS.BD_SERIALNO.VAL_INIT)
     *
     */
    private Short valInit;

    /**
     * null (NHIS.BD_SERIALNO.VAL)
     *
     */
    private Short val;

    /**
     * null (NHIS.BD_SERIALNO.CREATOR)
     *
     */
    private String creator;

    /**
     * null (NHIS.BD_SERIALNO.CREATE_TIME)
     *
     */
    private Date createTime;

    /**
     * null (NHIS.BD_SERIALNO.MODIFIER)
     *
     */
    private String modifier;

    /**
     * null (NHIS.BD_SERIALNO.MODITY_TIME)
     *
     */
    private Date modityTime;

    /**
     * null (NHIS.BD_SERIALNO.DEL_FLAG)
     *
     */
    private String delFlag;

    /**
     * null (NHIS.BD_SERIALNO.TS)
     *
     */
    private Date ts;

}