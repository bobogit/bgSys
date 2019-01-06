package com.imooc.product.exception;

import com.imooc.product.enums.ResultEnmu;

/**
 * Created By wangbo
 * 2019/1/5
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnmu resultEnmu) {
        super(resultEnmu.PRODUCT_NOT_EXISTS.getMsg());
        this.code = resultEnmu.PRODUCT_NOT_EXISTS.getCode();
    }
}
