package com.tkz.springboot.rabbitmq.publisher.task;

import com.alibaba.fastjson.JSONObject;
import com.tkz.springboot.rabbitmq.common.domain.MessageLog;
import com.tkz.springboot.rabbitmq.common.domain.Order;
import com.tkz.springboot.rabbitmq.common.mapper.MessageLogMapper;
import com.tkz.springboot.rabbitmq.publisher.constants.Constants;
import com.tkz.springboot.rabbitmq.publisher.producer.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@Component
public class TrySendTask {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private MessageLogMapper messageLogMapper;

    /**
     * 系统启动后 5 秒开启定时任务 10 秒执行一次
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void rabbitmqReSend() {

        Example example = Example.builder(MessageLog.class).build();
        example.createCriteria().andEqualTo("status", Constants.ORDER_SENDING).andLessThanOrEqualTo("nextRetry", new Date());

        /**
         *
         * 查询出下一次执行时间小于当前时间的日志记录并且状态为投递中，
         * 遍历结果集，判断重试次数是或大于3次，如果大于，将日志设置为投递失败.
         * 如果小于则尝试重新投递，并改变数据库中日志的尝试次数
         */
        messageLogMapper.selectByExample(example)
                .forEach(brokerMessageLog -> {
                    if (brokerMessageLog.getTryCount() >= 3) {
                        brokerMessageLog.setStatus(Constants.ORDER_SEND_FAILURE);
                        brokerMessageLog.setUpdateTime(new Date());
                        messageLogMapper.updateByPrimaryKeySelective(brokerMessageLog);

                    } else {
                        brokerMessageLog.setTryCount(brokerMessageLog.getTryCount() + 1);
                        brokerMessageLog.setUpdateTime(new Date());
                        messageLogMapper.updateByPrimaryKeySelective(brokerMessageLog);

                        try {
                            //重新投递信息
                            orderSender.send(JSONObject.parseObject(brokerMessageLog.getMessage(), Order.class));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
