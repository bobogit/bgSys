package com.imooc.product.enums;

import lombok.Getter;

/**
 * Created By wangbo
 * 2019/1/5
 */
@Getter
public enum ResultEnmu {

    PRODUCT_NOT_EXISTS(1, "商品不存在"), PRODUCT_STOCK_ERROR(2, "库存有误");


    private Integer code;
    private String msg;

    ResultEnmu(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
