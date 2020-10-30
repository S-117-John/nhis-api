package com.zebone.modules.mobile.patient.vo;

import com.zebone.common.entity.pv.PvEncounter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PvEncounterVO extends PvEncounter {

    private PiMasterVO piMaster;

    /**
     * 性别
     */
    public String gender;

    public String getGender() {
        String type = getDtSex();
        if(!StringUtils.isEmpty(type)){
            if("02".equals(type)){
                return "男";
            }else if("03".equals(type)){
                return "女";
            }
        }
        return gender;
    }

    /**
     * 诊断名称
     */
    public String diagName;

    /**
     * 科室
     */
    private String deptName;
}
