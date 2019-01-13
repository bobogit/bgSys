package com.imooc.product.service;

import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.input.DecreaseStockInput;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣减库存
     *
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
