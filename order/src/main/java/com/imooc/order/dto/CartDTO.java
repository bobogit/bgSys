package com.imooc.order.dto;

import lombok.Data;

/**
 * Created By wangbo
 * 2019/1/5
 */
@Data
public class CartDTO {

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    private String productId;

    private Integer productQuantity;
}
