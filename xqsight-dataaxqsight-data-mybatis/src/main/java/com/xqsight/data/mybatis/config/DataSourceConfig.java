package com.xqsight.data.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSourceConfigProperties dataSourceConfigProperties;

    @Bean(name = "commonDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceConfigProperties.getUrl());
        dataSource.setDriverClassName(dataSourceConfigProperties.getDriverClassName());
        dataSource.setUsername(dataSourceConfigProperties.getUsername());
        dataSource.setPassword(dataSourceConfigProperties.getPassword());
        dataSource.setInitialSize(dataSourceConfigProperties.getInitialSize());
        dataSource.setMaxActive(dataSourceConfigProperties.getMaxActive());
        dataSource.setMinIdle(dataSourceConfigProperties.getMinIdle());
        dataSource.setMaxWait(dataSourceConfigProperties.getMaxWait());
        dataSource.setMinEvictableIdleTimeMillis(dataSourceConfigProperties.getMinEvictableIdleTimeMillis());
        dataSource.setTimeBetweenEvictionRunsMillis(dataSourceConfigProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setValidationQuery(dataSourceConfigProperties.getValidationQuery());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfigProperties.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setRemoveAbandonedTimeout(dataSourceConfigProperties.getRemoveAbandonedTimeout());
        try {
            dataSource.setFilters(dataSourceConfigProperties.getFilters());
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
