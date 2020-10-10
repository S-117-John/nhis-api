package com.zebone.modules.mobile.cn.model;

import com.zebone.common.entity.cn.CnOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CnOrderParam {

    /**
     * 医生编码
     */
    private String doctorCode;

    /**
     * 住院号
     */
    private String codeIp;

    /**
     * 科室编码
     */
    private String codeDept;

    /**
     * 医嘱项目主键
     */
    private String pkOrd;

    private String euAlways;

    private String codeFreq;

    private Double quan;

    private String pkDeptExec;

    private String dateStart;

    private Integer firstNum;

    private List<String> stopOrderList;

    private String stopTime;

    private List<String> saveOrderList;

    private List<String> bdPdList;

    private List<CnOrder> cnOrderList;

    /**
     * 申请单号
     */
    private String codeApply;

    /**
     * 检查部位
     */
    private String descBody;

    /**
     * 检查目的
     */
    private String purpose;

    /**
     * 病情描述
     */
    private String noteDise;

    /**
     * 注意事项
     */
    private String note;
}
