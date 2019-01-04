package com.imooc.order.exception;

import com.imooc.order.enums.ResultEnmu;

public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnmu resultEnmu) {
        super(resultEnmu.getMessage());
        this.code = resultEnmu.getCode();
    }
}
