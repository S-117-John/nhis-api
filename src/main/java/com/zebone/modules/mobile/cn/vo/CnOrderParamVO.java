package com.zebone.modules.mobile.cn.vo;

import com.zebone.common.entity.cn.CnOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnOrderParamVO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private List<CnOrder> cnOrdList;

    private String code;

    private String codeIp;

    private String saveType;//0保存1签署

    private List<CnLabApplyVo> labApplyList;
    
    private List<CnRisApplyVo> risApplyList;

    /**
     * 科室编码
     */
    private String codeDept;

    /**
     * 医生编码
     */
    private String doctorCode;
}
