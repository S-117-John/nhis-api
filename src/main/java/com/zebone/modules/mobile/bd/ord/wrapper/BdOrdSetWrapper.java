package com.zebone.modules.mobile.bd.ord.wrapper;

import com.zebone.modules.mobile.bd.ord.vo.BdOrdSetVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BdOrdSetWrapper {

    private String title;
    private String key;
    private List<BdOrdSetVO> children;
}
