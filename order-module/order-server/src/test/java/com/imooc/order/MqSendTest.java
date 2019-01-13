package com.imooc.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MqSendTest extends  OrderApplicationTests{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue", "message=========================================" + new Date());
    }

    @Test
    public void sendOrder(){
        amqpTemplate.convertAndSend("myOrder", "computer", "now"+new Date());
    }

}
