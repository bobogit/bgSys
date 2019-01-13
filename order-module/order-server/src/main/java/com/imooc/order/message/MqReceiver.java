package com.imooc.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqReceiver {

//  1.  @RabbitListener(queues = "myQueue")   //不会自动创建队列
//  2.  @RabbitListener(queuesToDeclare = @Queue("myQueue"))    //自动创建队列
    //3. 自动创建, Exchange和Queue绑定
    @RabbitListener(bindings = @QueueBinding(
         value = @Queue("myQueue"),
         exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("message: {} ", message);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("processComputer: {} ", message);
    }
}
