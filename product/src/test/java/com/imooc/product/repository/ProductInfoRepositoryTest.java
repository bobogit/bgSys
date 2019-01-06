package com.imooc.product.repository;

import com.google.common.collect.Lists;
import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductInfoRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {

        List<ProductInfo> productList = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(productList.size() > 0);
    }

    @Test
    public void findByProductIdIn() {

        List<String> productIdList = Lists.newArrayList();
        productIdList.add("157875196366160022");
        productIdList.add("157875227953464068");
        productIdList.add("164103465734242707");

        List<ProductInfo> list = productInfoRepository.findByProductIdIn(productIdList);
        Assert.assertTrue(list.size() > 0);

    }
}