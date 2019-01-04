package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnmu {

    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "取消");

    private Integer code;
    private String message;

    OrderStatusEnmu(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
