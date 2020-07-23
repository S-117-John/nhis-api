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

	public List<CnOrder> cnOrdList;

    public String code;

    public String codeIp;

    public String saveType;//0保存1签署
    
    public List<CnLabApplyVo> labApplyList;
    
    public List<CnRisApplyVo> risApplyList;
    
    public String codeDept;
}
