package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum  ResultEnmu {

    PARAM_ERROR(1, "参数错误"),
    CART_EMPTY(2, "购物车为空"),;

    private Integer code;
    private String message;

    ResultEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
