package com.zebone.modules.mobile.bd.wrapper;

import com.zebone.modules.mobile.bd.vo.BdOrdSetVO;
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
