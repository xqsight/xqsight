/*
package com.xqsight.data.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


*/
/**
 * Druid的DataResource配置类
 *
 * @author wangganggang
 * @date 2017/03/24
 * <p>
 * 凡是被Spring管理的类，实现接口 EnvironmentAware
 * 重写方法 setEnvironment 可以在工程启动时，
 * 获取到系统环境变量和application配置文件中的变量。
 * <p>
 * <p>
 * 还有一种方式是采用注解的方式获取
 * @value("${变量的key值}") ：获取application配置文件中的变量。
 * <p>
 * 这里采用第一种要方便些
 *//*

@Configuration
public class DruidDataSourceConfig implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean
    public DataSource dataSource() {
        System.out.println("注入druid的datasours配置");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
        datasource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
        datasource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
        datasource.setPoolPreparedStatements(Boolean.valueOf(propertyResolver.getProperty("poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize")));
        datasource.setRemoveAbandoned(Boolean.valueOf(propertyResolver.getProperty("removeAbandoned")));
        datasource.setRemoveAbandonedTimeout(Integer.valueOf(propertyResolver.getProperty("removeAbandonedTimeout")));
        datasource.setLogAbandoned(Boolean.valueOf(propertyResolver.getProperty("logAbandoned")));
        datasource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty("testWhileIdle")));
        datasource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty("testOnBorrow")));
        datasource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty("testOnReturn")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(DataSourceTransactionManager transactionManager) {
        TransactionInterceptor ti = new TransactionInterceptor();
        ti.setTransactionManager(annotationDrivenTransactionManager());
        Properties properties = new Properties();
        properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
        properties.setProperty("update*", "PROPAGATION_REQUIRED");
        ti.setTransactionAttributes(properties);

        return ti;
    }

}*/
