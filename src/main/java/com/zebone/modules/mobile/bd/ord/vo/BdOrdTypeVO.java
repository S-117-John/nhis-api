package com.zebone.modules.mobile.bd.ord.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zebone.common.entity.bd.ord.BdOrdType;
import lombok.Data;

@Data
public class BdOrdTypeVO extends BdOrdType {

    private String title;

    private String key;

    @JsonProperty("isLeaf")
    private boolean leaf;
}
