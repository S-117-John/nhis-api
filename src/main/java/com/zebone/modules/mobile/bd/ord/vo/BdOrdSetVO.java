package com.zebone.modules.mobile.bd.ord.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebone.common.entity.bd.ord.BdOrdSet;
import lombok.Data;

@Data
public class BdOrdSetVO extends BdOrdSet {

    private String title;

    private String key;

    @JsonProperty("isLeaf")
    private boolean leaf;

}
