package com.imooc.product.service.impl;

import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnmu;
import com.imooc.product.exception.ProductException;
import com.imooc.product.input.DecreaseStockInput;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
//            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException(ResultEnmu.PRODUCT_NOT_EXISTS);
            }

            ProductInfo productInfo = productInfoOptional.get();
            //判断库存是否足够
            int result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnmu.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

//            productInfo.setProductStock(productInfo.getProductStatus() - cartDTO.getProductQuantity());
//            productInfoRepository.save(productInfo);
        }
    }
}
