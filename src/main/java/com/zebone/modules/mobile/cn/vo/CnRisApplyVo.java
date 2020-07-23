package com.zebone.modules.mobile.cn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.zebone.common.entity.cn.CnRisApply;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnRisApplyVo extends CnRisApply {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String euOrdtype; //科研医嘱标志

    public String flagFit;//适应症标志

    
}
