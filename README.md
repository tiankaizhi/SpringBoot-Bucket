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
  <a href="https://blog.csdn.net/weixin_44197154/"><img src="https://img.shields.io/badge/CSDN-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-success.svg" alt="CSDN"></a>
  <a href="https://www.cnblogs.com/tkzL/"><img src="https://img.shields.io/badge/%E5%8D%9A%E5%AE%A2%E5%9B%AD-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-yellow.svg" alt="博客园"></a>
  <a href="https://my.oschina.net/tiankaizhi/"><img src="https://img.shields.io/badge/%E5%BC%80%E6%BA%90%E4%B8%AD%E5%9B%BD-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-37a14e.svg" alt="开源中国"></a>
  <a href="https://segmentfault.com/u/tiankaizhi/"><img src="https://img.shields.io/badge/%E6%80%9D%E5%90%A6-%E5%8D%9A%E5%AE%A2%E5%9C%B0%E5%9D%80-blueviolet.svg" alt="思否"></a>
  <a href="https://www.jianshu.com/u/9b5933251e36/"><img src="https://img.shields.io/badge/%E7%AE%80%E4%B9%A6-%E6%96%87%E7%AB%A0-ea6f5a.svg" alt="简书"></a>
  <a href="https://www.zhihu.com/people/tiankaizhi/"><img src="https://img.shields.io/badge/%E7%9F%A5%E4%B9%8E-%E7%9F%A5%E4%B9%8E%E5%9C%B0%E5%9D%80-green.svg" alt="知乎"></a>
</p>

## 前言

### Why SpringBoot-Bucket?

SpringBoot 现今在企业级开发中的地位在这里就不赘述了，SpringBoot Bucket 是在 SpringBoot 框架基础之上开发的适用于企业级开发的各种技术方案的整合。项目中涉及到的一些技术方案在笔者的个人博客中有详细介绍。

## 项目结构

![](https://img2020.cnblogs.com/blog/1326851/202004/1326851-20200404235132819-413718438.png)

## 项目模块

模块名称                    | 说明
----------------------------|------------------------------------------------------------------------------------------
springboot-rabbitmq         | Springboot 集成 RabbitMQ 实现生产端消息 100% 投递成功方案

## 技术选型

| 技术                 | 说明                | 官网                                                 |
| -------------------- | ------------------- | ---------------------------------------------------- |
| SpringBoot           | 容器+MVC框架        | https://spring.io/projects/spring-boot               |
| MyBatis              | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html       |
| RabbitMq             | 消息队列            | https://www.rabbitmq.com/                            ||
| Docker               | 应用容器引擎        | https://www.docker.com                               |
| Druid                | 数据库连接池        | https://github.com/alibaba/druid                     |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok               |

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

| 工具          | 版本号 | 下载                                                         |
| ------------- | ------ | ------------------------------------------------------------ |
| JDK           | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql         | 8.0    | https://www.mysql.com/                                       |
| RabbitMq      | 3.8.3 | http://www.rabbitmq.com/download.html                        |
| Erlang      | 22.3 | http://www.rabbitmq.com/download.html                        |

## 环境部署

- 在 IDEA 里面直接运行 Application.java 的 main 函数。
- 另一种方式是执行 <code><font color="#de2c58">mvn clean package</font></code> 命令打包上传到服务器上，执行命令 <code><font color="#de2c58">java -Xms64m -Xmx1024m -jar xxx.jar</font></code> 运行项目。

**注意：每个子模块的 README.md 文件详细描述了环境所需配置**。

## 公众号

想要了解更多，关注公众号「**我们都是小白鼠**」第一时间获取。

![公众号图片](https://img2020.cnblogs.com/blog/1326851/202003/1326851-20200327151848769-1614210938.png)

## 许可证

[Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

Copyright (c) 2020 我们都是小白鼠

## 问题反馈

1. 欢迎提 issue 一起完善这个项目
2. Email: tkz_mailbox@163.com
