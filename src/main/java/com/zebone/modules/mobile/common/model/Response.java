package com.zebone.modules.mobile.common.model;

import lombok.Data;

@Data
public class Response<T> {

    private Integer code;

    private String message;

    private T data;
}
