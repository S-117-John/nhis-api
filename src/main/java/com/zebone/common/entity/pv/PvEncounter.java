package com.zebone.common.entity.pv;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * PV_ENCOUNTER
 * 就诊信息
 * @author 
 */
@Data
@Entity
public class PvEncounter implements Serializable {

    @Id
    private String pkPv;

    private String pkOrg;

    private String pkPi;

    /**
     * 门诊号或门诊流水号； 住院号或住院流水号
     */
    private String codePv;

    /**
     * 1门诊，2急诊，3住院，4体检，5家庭病床
     */
    private String euPvtype;

    private Date dateBegin;

    private Date dateEnd;

    /**
     * 0 登记，1 就诊，2 结束，3 结算，9 退诊
     */
    private String euStatus;

    /**
     * 仅对住院流程，急诊观察有效
     */
    private String flagIn;

    /**
     * 0表示未出院结算 1表示出院结算
     */
    private String flagSettle;

    private String namePi;

    private String dtSex;

    private String agePv;

    private String address;

    private String dtMarry;

    /**
     * 门诊：医生接受时写入本次临床科室；住院：病区接受时写入本次对应的临床科室
     */
    private String pkDept;

    /**
     * 住院：病区接受时写入本次对应的部门
     */
    private String pkDeptNs;

    /**
     * 门诊医生看诊时间
     */
    private Date dateClinic;

    /**
     * 病区接收入科时间
     */
    private Date dateAdmit;

    /**
     * 在医疗组管理模式下的对应医疗组主键，可为空
     */
    private String pkWg;

    /**
     * 门诊慢性病，急诊观察时，住院床位。调床是实时更新
     */
    private String bedNo;

    /**
     * 入院登记时，根据入院通知单填入收治医生，若无入院通知单则操作员手工录入
     */
    private String pkEmpTre;

    private String nameEmpTre;

    /**
     * 门诊：医生接受时写入接诊医生编码；
   住院：病区接受时写入本次对应的责任医生，以后执行患者医生调整时写入调整后的医生
     */
    private String pkEmpPhy;

    private String nameEmpPhy;

    /**
     * 住院：病区接受时写入本次对应的责任护士，以后执行患者护士调整时写入调整后的护士
     */
    private String pkEmpNs;

    private String nameEmpNs;

    private String pkInsu;

    /**
     * 居民、军人、老人等
     */
    private String pkPicate;

    private String pkEmpReg;

    private String nameEmpReg;

    /**
     * 登记日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date dateReg;

    /**
     * 门诊：退号；住院：当日退院
     */
    private String flagCancel;

    private String pkEmpCancel;

    private String nameEmpCancel;

    private Date dateCancel;

    /**
     * 0和null  未产生随访，1 生成随访计划，2 随访过程，3 随访结束，9 随访终止
     */
    private String euStatusFp;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modityTime;

    private String delFlag;

    private BigDecimal height;

    private BigDecimal weight;

    private Date ts;

    private String addrcodeRegi;

    private String addrRegi;

    private String addrRegiDt;

    private String postcodeRegi;

    private String addrcodeCur;

    private String addrCur;

    private String addrCurDt;

    private String postcodeCur;

    private String unitWork;

    private String telWork;

    private String postcodeWork;

    private String nameRel;

    private String dtRalation;

    private String telRel;

    private String addrRel;

    private String dtPvsource;

    private String euPvmode;

    private String dtHprealtype;

    private String dtSpecunit;

    private String flagSpec;

    private String euLocked;

    private String note;

    private String euDisetype;

    private String dtIdtypeRel;

    private String idnoRel;

    private String flagMi;

    private String nameSpouse;

    private String idnoSpouse;

    private String descSymp;

    private String descEpid;

    private String descTreat;

    private String pkDeptDist;

    private String dtSpcdtype;

    private static final long serialVersionUID = 1L;
}