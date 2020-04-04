package com.tkz.springboot.redis.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author tiankaizhi
 * @since 2020/03/29
 */
@Service
public class LockServiceImpl implements LockService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedissonClient redissonSingle;

    /*
     * 尝试获取锁
     *
     * 这里有两点要注意：
     * 1. waitTime 是等待时间，超过此时间线程还未获取锁则返回 false
     * 2. leaseTime 是线程持有锁的时间，超过此时间后锁会自动释放
     * 但是，在这里有一个问题：如果 leaseTime 是一个大于 0 的数，当超过此时间后，锁会自动失效，此时如果你的业务未执行完，其他线程也会进来执行该任务
     *
     * 要想实现锁自动续期，leaseTime 要设置成 -1，当 leaseTime 设置成 -1 时，锁的默认锁定时间是 30s，并且会通过 watchDog 机制，
     * 每隔 10s 会检测一下业务是否执行完成，如果未执行完，会将锁的时间重新置为 30s
     *
     * 注意：这里还有一个问题，当出现竞争的时候，等待锁的线程的状态是怎样的，且这些等待锁的线程重新去尝试获取锁的机制又是怎样的？
     *
     * 假设有三个线程，A 拿到了锁，B 和 C 并不是通过 while(true) 循环，一次接着一次的尝试，这样会造成大量无效的锁申请。
     * 也不是通过 Thread.sleep，在上面的 while 方案中增加睡眠时间以降低锁申请次数，因为睡眠的时间设置比较难控制的。
     * Redisson 的方式是基于信息量，当锁被其它进程占用时，当前线程订阅锁的释放事件，一旦锁释放会发消息通知待等待的线程进行竞争，有效的解决了无效的锁申请情况。
     * 当达到最大等待时间还未收到通知的话，此时等待的线程就会取消订阅并且返回 false
     *
     * 续期实现的原理（watchDog 机制）：当 A 获取到锁之后，会向 RedissonLock.EXPIRATION_RENEWAL_MAP 里面写入持有锁的线程 id，然后启动一个定时任务（netty 提供的定时任务框架）
     * 该定时任务会每隔 10s 后去扫描一下这个 map，将 map 里面的该线程 id 对应的锁的时间重置。解锁的操作其中一步就是将该 map 里面的线程 id 删除
     *
     * 解锁步骤：
     * 1. 如果锁存在则删除(lua 脚本实现)
     * 2. 发送一个解锁的消息，以此唤醒等待队列中的线程重新竞争锁（通过 lua 脚本 1486642678.493712 [0 lua] "publish" "redisson_lock__channel:）
     * 3. 将 RedissonLock.EXPIRATION_RENEWAL_MAP 里面的线程 id 删除，并且取消该定时任务线程
     *
     */
    @Override
    public void tryLock() {
        // 创建锁
        RLock lock = redissonSingle.getLock("myLock");
        boolean lockFlag = false;

        try {
            lockFlag = lock.tryLock(5, -1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (lockFlag) {
            // 获取锁成功，执行业务
            try {
                logger.info("获取锁成功，执行业务");
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁
                lock.unlock();
                logger.info("业务执行完成，释放锁");
            }
        } else {
            logger.error("获取锁失败");
        }
    }

    @Override
    public void lock() {
        // 创建锁
        RLock lock = redissonSingle.getLock("myLock");
        try {
            // 默认枷锁时间是 30s，没有最大等待时间，会一直等待知道锁释放，另一个线程会再次获取锁
            lock.lock();
            logger.info("获取锁成功, 执行任务!");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
            logger.info("任务执行完毕, 释放锁!");
        }
    }

}
