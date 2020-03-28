package com.tkz.springboot.rabbitmq.publisher.controller;

import com.tkz.springboot.rabbitmq.common.domain.Order;
import com.tkz.springboot.rabbitmq.publisher.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author tiankaizhi
 * @since 2019/1/25 上午9:25
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "generateOrder")
    public String send() {
        Order order = Order.builder()
                .name(UUID.randomUUID().toString())
                .messageId(UUID.randomUUID().toString() + System.currentTimeMillis()).build();
        orderService.createOrder(order);
        return "success";
    }

}
