package com.tkz.springboot.rabbitmq.publisher.producer;


import com.tkz.springboot.rabbitmq.common.domain.Order;
import com.tkz.springboot.rabbitmq.publisher.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void setOrderSender() throws InterruptedException {
        Order order = new Order();
        order.setName(UUID.randomUUID().toString());
        order.setMessageId(UUID.randomUUID().toString() + System.currentTimeMillis());
        orderService.createOrder(order);

        Thread.sleep(60000 * 5);
    }

}
