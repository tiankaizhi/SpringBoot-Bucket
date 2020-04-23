package com.tkz.springboot.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(128);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *
     */
    @Test
    public void testHypeLogLog() {
        CountDownLatch countDownLatch = new CountDownLatch(1000000);
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisTemplate.opsForHyperLogLog().add("index.html", "user" + finalI);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
            System.out.println(redisTemplate.opsForHyperLogLog().size("index.html"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * set
     */
    @Test
    public void testSet() {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisTemplate.opsForValue().set("key" + finalI, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * scan
     */
    @Test
    public void testScan() {
        Set<String> keySet = (Set<String>) redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> result = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("key9*").count(10).build())) {
                while (cursor.hasNext()) {
                    byte[] bytes = cursor.next();
                    result.add(new String(bytes, StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });
        System.out.println(keySet);
    }

    /**
     * hset
     */
    @Test
    public void testHset() {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisTemplate.opsForHash().put("field", "key" + finalI, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hscan
     */
    @Test
    public void testHScan() {
        try (Cursor<Map.Entry<String, Set<String>>> cursor = redisTemplate.opsForHash().scan("field", ScanOptions.scanOptions().match("key99*").count(10).build())) {
            while (cursor.hasNext()) {
                Map.Entry<String, Set<String>> entry = cursor.next();
                System.out.println("key: " + entry.getKey() + "\t value: " + entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
