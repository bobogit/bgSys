package com.imooc.order.service.impl;

import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.OrderStatusEnmu;
import com.imooc.order.enums.PayStatusEnmu;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.KeyUtil;
import com.imooc.product.client.ProductClient;
import com.imooc.product.input.DecreaseStockInput;
import com.imooc.product.output.ProductOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMasterRepository orderMasterRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();

        //查询商品信息
        List<String> productList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductOutput> productInfoList = productClient.listForOrder(productList);

        //计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductOutput productOutput : productInfoList) {
                if (orderDetail.getProductId().equals(productOutput.getProductId())) {
                    //单价*数量
                    orderAmount = new BigDecimal(orderDetail.getProductQuantity()).multiply(productOutput.getProductPrice())
                            .add(orderAmount);
                    BeanUtils.copyProperties(productOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //扣库存(调用商品服务)
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnmu.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnmu.NEW.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
