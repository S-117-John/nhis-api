package com.zebone.modules.mobile.patient.vo;

import com.zebone.common.entity.pi.PiMaster;
import lombok.Data;

@Data
public class PiMasterVO extends PiMaster {

    private PiCateVO piCate;
}
