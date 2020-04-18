## SpringBoot Bucket

<p>
  <a href="https://www.apache.org/licenses/LICENSE-2.0"><img src="https://img.shields.io/badge/license-Apache%202-blue" alt="Apache2 协议"></a>
  <img src="https://img.shields.io/badge/release-v1.0.0-blue">
  <img src="https://img.shields.io/badge/build-passing-brightgreen">
  <img src="https://img.shields.io/badge/downloads-5M-brightgreen">
</p>

<p>
  <a href="#公众号"><img src="https://img.shields.io/badge/%E5%85%AC%E4%BC%97%E5%8F%B7-%E6%88%91%E4%BB%AC%E9%83%BD%E6%98%AF%E5%B0%8F%E7%99%BD%E9%BC%A0-blue.svg" alt="公众号"></a>
  <a href="https://github.com/tiankaizhi/SpringBoot-Bucket"><img src="https://img.shields.io/badge/GitHub-%E9%A1%B9%E7%9B%AE%E5%9C%B0%E5%9D%80-9cf.svg" alt="github"></a>
  <a href="https://gitee.com/tkz_wu/SpringBoot-Bucket"><img src="https://img.shields.io/badge/%E7%A0%81%E4%BA%91-%E9%A1%B9%E7%9B%AE%E5%9C%B0%E5%9D%80-yellowgreen.svg" alt="码云"></a>
  <a href="https://juejin.im/user/5cd976276fb9a03242651e7e"><img src="https://img.shields.io/badge/%E6%8E%98%E9%87%91-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-green.svg" alt="掘金社区"></a>
  <a href="https://www.cnblogs.com/tkzL/"><img src="https://img.shields.io/badge/%E5%8D%9A%E5%AE%A2%E5%9B%AD-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-yellow.svg" alt="博客园"></a>
</p>

## 前言

SpringBoot Bucket 是在 SpringBoot 框架基础之上开发的 **适用于企业级开发的各种技术方案的整合**。项目中涉及到的一些技术方案的原理在笔者的个人博客和微信公众号中有详细分析。

## 项目结构

![](https://img2020.cnblogs.com/blog/1326851/202004/1326851-20200418135525906-1250998919.png)

## 项目模块

模块名称                    | 说明
----------------------------|----------------------------------------------------------
rabbitmq         | Springboot 集成 RabbitMQ 实现 Producer 端消息 100% 投递成功方案
distributed-lock           | 基于 Springboot 使用 Redisson 实现分布式锁方案
redis         | Springboot 集成 Redis 实现常规用法
zookeeper           | 基于 Zookeeper 使用 Curator 实现分布式锁方案

## 技术选型

| 技术                 | 说明                | 官网                                                 |
| -------------------- | ------------------- | ---------------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot               |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html       |
| RabbitMq             | 消息队列            | https://www.rabbitmq.com/                            |
| Redis             | 缓存            | https://redis.io/                            |
| Zookeeper               | 分布式协调中心        | https://zookeeper.apache.org/                               |

## 环境搭建

### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发 IDE             | https://www.jetbrains.com/idea/download         |
| MobaXterm       | Linux 远程连接工具   | https://mobaxterm.mobatek.net/ |
| Navicat       | 数据库连接工具      | https://www.navicat.com.cn/             |
| PowerDesigner | 数据库设计工具      | http://powerdesigner.de/                        |
| Processon    | 思维导图设计工具    | https://www.processon.com/              |
| Snipaste      | 屏幕截图工具        | https://www.snipaste.com/                       |
| Postman       | API 接口调试工具      | https://www.postman.com/                        |
| Atom        | Markdown编辑器      | https://atom.io/                              |
| Typora        | Markdown编辑器      | https://typora.io/                              |

### 开发环境

| 工具          | 版本号 | 下载地址                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 8.0    | https://www.mysql.com/                                  |
| RabbitMq      | 3.8.3 | http://www.rabbitmq.com/download.html                    |
| Erlang      | 22.3 | http://www.rabbitmq.com/download.html                       |
| Redis      | 5.0.8 | https://redis.io/download                        |
| Zookeeper      | 3.4.6 | https://zookeeper.apache.org/releases.html#download     |

## 公众号

项目涉及到的一些模块，如 Redisson 实现分布式锁的原理，Zookeeper 实现分布式锁的原理等都在微信公众号中有详细的分析，想要深入了解的朋友可以关注公众号 「**我们都是小白鼠**」。

![公众号图片](https://img2020.cnblogs.com/blog/1326851/202003/1326851-20200327151848769-1614210938.png)

## 许可证

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

Copyright (c) 2020 我们都是小白鼠

## 问题反馈

1. 欢迎提 issue 一起完善这个项目
2. Email: tkz_mailbox@163.com
