package com.imooc.product.client;

import com.imooc.product.input.DecreaseStockInput;
import com.imooc.product.output.ProductOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created By wangbo
 * 2019/1/5
 */
@FeignClient(name = "product-server")  //访问应用的名称
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductOutput> listForOrder(List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
