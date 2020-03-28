package com.tkz.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author tiankaizhi
 * @since 2020/03/25
 */
@SpringBootApplication
@MapperScan("com.tkz.springboot.rabbitmq.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
