package com.tkz.springboot.redis.service;

import java.util.concurrent.TimeUnit;

/**
 * @author tiankaizhi
 * @since 2020/03/29
 */
public interface LockService {

    void tryLock();

    void lock();

}
