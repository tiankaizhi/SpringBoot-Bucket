package com.tkz.springboot.rabbitmq.customer.consumer;

import com.rabbitmq.client.Channel;
import com.tkz.springboot.rabbitmq.common.domain.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tiankaizhi
 * @since 2019/1/25 上午9:25
 */
@Component
public class OrderReceiver {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "order-queue", durable = "true"),
                    exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
                    key = "order.*"
            )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        System.out.println("----收到消息，开始消费-----");
        System.out.println("d订单id：" + order.getId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        // .......... 业务处理

        /**
         *  取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        channel.basicAck(deliveryTag, false);

        // 如果业务处理出现异常，可以拒绝接收这条消息
//        channel.basicReject(deliveryTag, false);

        // multiple 参数设置为 false 则表示拒绝编号为 deliveryTag 的这一条消息，这时候 basicNack 和 basicReject 方法一样;
        // multiple 参数设置为 true 则表示拒绝 deliveryTag 编号之前所有未被当前消费者确认的消息。

        // 注意: 将 channel.basicRegict 或者 channel.basicNack 中的 requeue 设置为 false,可以启用 "死信队列" 的可能。
        // 如果设置为 true 则消息重回队列会再次被消费
//        channel.basicNack(deliveryTag, false, false);
        System.out.println("--------消费完成--------");
    }

}
