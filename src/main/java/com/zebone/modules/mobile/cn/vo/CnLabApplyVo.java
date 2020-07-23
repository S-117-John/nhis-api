package com.zebone.modules.mobile.cn.vo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.zebone.common.entity.cn.CnLabApply;
import com.zebone.common.entity.cn.CnOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnLabApplyVo extends CnLabApply {

    public String euOrdtype; //科研医嘱标志

    public String flagFit;//适应症标志

   
}
