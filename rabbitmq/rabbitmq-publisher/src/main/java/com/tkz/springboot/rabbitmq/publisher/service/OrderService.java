package com.tkz.springboot.rabbitmq.publisher.service;

import com.tkz.springboot.rabbitmq.common.domain.Order;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
public interface OrderService {

    void createOrder(Order order);

}
