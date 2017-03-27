package com.xqsight.data.mybatis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 只提供了常用的属性，如果有需要，自己添加
 *
 * @author wangganggang
 * @date 2017/03/24.
 */
@Data
@Component
public class DataSourceConfigProperties {

    @Value("${xqsight.jdbc.url}")
    private String url;
    @Value("${xqsight.jdbc.username}")
    private String username;
    @Value("${xqsight.jdbc.password}")
    private String password;
    @Value("${xqsight.jdbc.driverClassName}")
    private String driverClassName;

    @Value("${xqsight.jdbc.initialSize}")
    private int initialSize;
    @Value("${xqsight.jdbc.maxActive}")
    private int maxActive;
    @Value("${xqsight.jdbc.maxIdle}")
    private int maxIdle;
    @Value("${xqsight.jdbc.minIdle}")
    private int minIdle;
    @Value("${xqsight.jdbc.maxWait}")
    private long maxWait;
    @Value("${xqsight.jdbc.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${xqsight.jdbc.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${xqsight.jdbc.validationQuery}")
    private String validationQuery;
    @Value("${xqsight.jdbc.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${xqsight.jdbc.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${xqsight.jdbc.testOnReturn}")
    private boolean testOnReturn;
    @Value("${xqsight.jdbc.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${xqsight.jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${xqsight.jdbc.filters}")
    private String filters;
    @Value("${xqsight.jdbc.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${xqsight.jdbc.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${xqsight.jdbc.logAbandoned}")
    private boolean logAbandoned;
}