package com.zebone.common.entity.bd.term;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * BD_TERM_FREQ
 * @author 
 */
@Data
@Entity
public class BdTermFreq implements Serializable {
    /**
     * 频次主键
     */
    @Id
    private String pkFreq;

    /**
     * 所属机构
     */
    private String pkOrg;

    /**
     * 频次编码:例如 qd
     */
    private String code;

    /**
     * 频次名称:例如 ，每日一次
     */
    private String name;

    /**
     * 打印名称
     */
    private String namePrint;

    /**
     * 频次周期类型:0按天执行 1按周执行  2按小时执行 3按分钟执行
     */
    private String euCycle;

    /**
     * 频次周期数
     */
    private Integer cntCycle;

    /**
     * 计划频次标志
     */
    private String flagPlan;

    /**
     * 周期执行次数
     */
    private Integer cnt;

    /**
     * 重复类型:0长期，1临时
     */
    private String euAlways;

    /**
     * 频次描述
     */
    private String note;

    /**
     * 拼音码
     */
    private String spcode;

    /**
     * 自定义码
     */
    private String dCode;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 0:未删除
   1:已删除
     */
    private String delFlag;

    private Date ts;

    private Integer sortno;

    private String dtFreqtype;

    private String oldId;

    private String flagEmer;

    private String euRange;

    private String euExtime;

    private String euFirsttype;

    private static final long serialVersionUID = 1L;
}