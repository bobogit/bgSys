package com.imooc.order.repository;

import com.imooc.order.OrderApplicationTests;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.enums.OrderStatusEnmu;
import com.imooc.order.enums.PayStatusEnmu;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("13636699338");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid("11011110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnmu.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnmu.NEW.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);

        Assert.assertTrue(result != null);
    }
}