package com.zebone.modules.mobile.bd.vo;

import com.zebone.common.entity.bd.ord.BdOrdSet;
import lombok.Data;

@Data
public class BdOrdSetVO extends BdOrdSet {

    private String title;

    private String key;

    public String getTitle() {
        return getName();
    }

    public String getKey() {
        return getCode();
    }
}
