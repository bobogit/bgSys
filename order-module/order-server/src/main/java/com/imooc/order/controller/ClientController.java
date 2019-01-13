package com.imooc.order.controller;

import com.google.common.collect.Lists;
import com.imooc.product.client.ProductClient;
import com.imooc.product.input.DecreaseStockInput;
import com.imooc.product.output.ProductOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

//    @Autowired
//    LoadBalancerClient loadBalancerClient;
//
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 1.第一种方式 直接使用restTemplate, url写死
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/msg", String.class);

        //2. 第二种方式 使用loadbalancerClient通过应用名获取url,然后再使用restTemplate
//        ServiceInstance productInstance = loadBalancerClient.choose("product");

//        String url = String.format("http://%s:%s/msg", productInstance.getHost(), productInstance.getPort());
//        log.info("url :" + url);
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url, String.class);

        //3. 第三种方式(利用 @LoadBalanced, 可在restTemplate中使用应用名称)
//        String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        //4. 使用feign访问
        String response = productClient.productMsg();

        log.info(response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductOutput> productOutputList = productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.info("response={}", productOutputList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String decreaseStock() {
        productClient.decreaseStock(Lists.newArrayList(new DecreaseStockInput("164103465734242707", 3)));
        return "ok";
    }
}
