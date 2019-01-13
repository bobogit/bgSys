package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnmu {

    NEW(0, "等待支付"),
    FINISH(1, "支付成功");

    private Integer code;
    private String message;

    PayStatusEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
