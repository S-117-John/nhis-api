package com.zebone.modules.mobile.patient.vo;

import com.zebone.common.entity.pv.PvEncounter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PvEncounterVO extends PvEncounter {

    private PiMasterVO piMaster;
}
