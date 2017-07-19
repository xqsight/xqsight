package com.xqsight.config.remain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 只提供了常用的属性，如果有需要，自己添加
 *
 * @author liuzh
 * @since 2017/2/5.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "xqsight.jdbc")
@Component
public class DruidProperties {
    //@Value("${xqsight.jdbc.url}")
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    private int initialSize;
    private int maxActive;
    private int maxIdle;
    private int minIdle;
    private long maxWait;
    private long minEvictableIdleTimeMillis;
    private long timeBetweenEvictionRunsMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private boolean removeAbandoned;
    private int removeAbandonedTimeout;
    private boolean logAbandoned;
}