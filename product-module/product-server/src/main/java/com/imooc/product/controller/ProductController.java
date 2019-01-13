package com.imooc.product.controller;

import com.google.common.collect.Lists;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.input.DecreaseStockInput;
import com.imooc.product.output.ProductOutput;
import com.imooc.product.service.CategoryService;
import com.imooc.product.service.ProductService;
import com.imooc.product.util.ResultVOUtil;
import com.imooc.product.vo.ProductInfoVO;
import com.imooc.product.vo.ProductVO;
import com.imooc.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<ProductInfoVO> list() {

        List<ProductInfo> productInfoList = productService.findUpAll();

        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList = Lists.newArrayList();

        for (ProductCategory category : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            List<ProductInfoVO> productInfoVOList = Lists.newArrayList();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType() == category.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务使用)
     *
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductOutput> listForOrder(@RequestBody List<String> productIdList) {
        List<ProductInfo> productInfoList = productService.findList(productIdList);
        List<ProductOutput> productInfoVOList = Lists.newArrayList();

        productInfoList.forEach(e->{
            ProductOutput vo = new ProductOutput();
            BeanUtils.copyProperties( e, vo);
            productInfoVOList.add(vo);
        });
        return productInfoVOList;
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        productService.decreaseStock(decreaseStockInputList);
    }

}
