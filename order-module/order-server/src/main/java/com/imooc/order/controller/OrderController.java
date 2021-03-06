package com.imooc.order.controller;

import com.google.common.collect.Maps;
import com.imooc.order.VO.ResultVO;
import com.imooc.order.convert.OrderForm2OrderDTOConvert;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.ResultEnmu;
import com.imooc.order.exception.OrderException;
import com.imooc.order.form.OrderForm;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1. 参数校验
     * 2. 查询商品信息
     * 3. 计算总价
     * 4. 扣库存
     * 5. 订单入库
     * 参数例子:
     * {
     *     name: "XX"
     *     phone: "1321321321"
     *     address: "fdsfdsfds"
     *     openid: "jdklsfjdklsjf"
     *     items:[{productId:"321321321",productQuantity:2},...]
     * }
     */
    @PostMapping("create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnmu.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车信息为空");//
            throw new OrderException(ResultEnmu.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = Maps.newHashMap();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

}
