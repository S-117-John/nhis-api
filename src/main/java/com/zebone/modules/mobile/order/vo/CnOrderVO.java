package com.zebone.modules.mobile.order.vo;

import com.zebone.common.entity.bd.ord.BdOrdLab;
import com.zebone.common.entity.bd.ord.BdOrdRis;
import com.zebone.common.entity.cn.CnOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CnOrderVO extends CnOrder {

    private BdOrdRis bdOrdRis;

    private BdOrdLab bdOrdLab;
}
