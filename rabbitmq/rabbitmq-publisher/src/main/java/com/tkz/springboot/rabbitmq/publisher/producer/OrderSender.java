package com.tkz.springboot.rabbitmq.publisher.producer;

import com.tkz.springboot.rabbitmq.common.domain.MessageLog;
import com.tkz.springboot.rabbitmq.common.domain.Order;
import com.tkz.springboot.rabbitmq.common.mapper.MessageLogMapper;
import com.tkz.springboot.rabbitmq.publisher.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@Component
public class OrderSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageLogMapper messageLogMapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        /**
         * Confirm 机制
         *
         * 1. 在 channel 中开启确认模式：channel.confirmSelect();
         * 2. 在 channel 中添加监听：addConfirmListener();
         * 3. 发生 Nack 的情况：磁盘写满、Queue达到上线、MQ其他异常
         * 4. ack 和 Nack 都收不到的情况：就要定时任务去处理
         *
         * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
         * @param ack  是否投递成功
         * @param cause 失败原因
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String messageId = correlationData.getId();

            Example example = Example.builder(MessageLog.class).build();
            example.createCriteria().andEqualTo("messageId", messageId);
            MessageLog messageLog = null;
            try {
                messageLog = messageLogMapper.selectByExample(example).get(0);
            } catch (IndexOutOfBoundsException e) {
                logger.error("不存在 messageId:{} 的日志记录", messageId);
            }

            //返回成功，表示消息被正常投递
            if (ack) {
                messageLog.setStatus(Constants.ORDER_SEND_SUCCESS);
                messageLog.setUpdateTime(new Date());
                messageLogMapper.updateByPrimaryKeySelective(messageLog);

                logger.info("信息投递成功，messageId:{}", messageLog.getMessageId());

            } else {
                logger.error("消费信息失败，messageId:{} 原因:{}", messageLog.getMessageId(), cause);
            }
        }
    };


    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {

        /**
         * Return 机制
         *
         * 如果发送的消息，Exchange 不存在或者 RouteKey 路由不到，这时就需要 returnListener。
         * Mandatory：true-监听器接受到这些不可达的消息，false-broker 会自动删除这些消息。
         * 消费端自定义监听：继承 DefaultConsumer
         *
         * @param message 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
         * @param replyCode  是否投递成功
         * @param replyText 失败原因
         * @param exchange 失败原因
         * @param routingKey 失败原因
         */
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            logger.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}", exchange, routingKey, replyCode, replyText);
        }
    };


    /**
     * 信息投递的方法
     *
     * @param order
     * @throws Exception
     */
    public void send(Order order) throws Exception {
        //设置投递回调
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());

        rabbitTemplate.convertAndSend("order-exchange", "order.abcd", order, correlationData);
    }

}
