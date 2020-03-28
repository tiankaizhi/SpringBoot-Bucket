package com.tkz.springboot.rabbitmq.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.IdentityDialect;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message_log")
public class MessageLog implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true, dialect = IdentityDialect.MYSQL)
    @Column(name = "message_id")
    private String messageId;

    @Column(name = "message")
    private String message;

    @Column(name = "try_count")
    private Integer tryCount;

    @Column(name = "status")
    private String status;

    @Column(name = "next_retry")
    private Date nextRetry;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}