package com.tkz.springboot.rabbitmq.publisher.service;

import com.alibaba.fastjson.JSON;
import com.tkz.springboot.rabbitmq.common.domain.MessageLog;
import com.tkz.springboot.rabbitmq.common.domain.Order;
import com.tkz.springboot.rabbitmq.common.mapper.MessageLogMapper;
import com.tkz.springboot.rabbitmq.common.mapper.OrderMapper;
import com.tkz.springboot.rabbitmq.publisher.constants.Constants;
import com.tkz.springboot.rabbitmq.publisher.producer.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MessageLogMapper messageLogMapper;

    @Value("${spring.rabbitmq.overtime}")
    private int minutes;

    @Autowired
    private OrderSender orderSender;

    @Override
    @Transactional
    public void createOrder(Order order) {

        //插入订单表
        orderMapper.insertSelective(order);

        //插入 rabbitmq 投递信息日志表
        Date date = new Date();
        MessageLog brokerMessageLog = MessageLog.builder()
                .messageId(order.getMessageId())
                .message(JSON.toJSONString(order))
                .status(Constants.ORDER_SENDING)
                .createTime(new Date())
                .nextRetry(new Date(date.getTime() + minutes * 60000))
                .tryCount(0).build();

        messageLogMapper.insertSelective(brokerMessageLog);

        try {
            orderSender.send(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
