package com.xqsight.data.mybatis.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.xqsight.data.mybatis.datasource.DynamicDataSource;
import com.xqsight.data.mybatis.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wangganggang on 2017/7/18.
 */
@Configuration
@EnableTransactionManagement(order = 2)//由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(basePackages = {"com.xqsight.**.mapper","com.tangchao.**.mapper"})
public class DataSourceConfig {
    protected Logger logger = LogManager.getLogger(DataSourceConfig.class);

    @ConfigurationProperties("spring.datasource.master")
    @Bean(name = "masterDataSource")
    @Primary
    public DruidDataSource masterDataSource() {
        return new DruidDataSource();
    }


    @ConfigurationProperties("spring.datasource.slave")
    @Bean(name = "slaveDataSource")
    public DruidDataSource slaveDataSource() {
        return new DruidDataSource();
    }


    @Bean
    public DynamicDataSource dataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.MASTER, masterDataSource);
        targetDataSources.put(DataSourceEnum.SLAVE, slaveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为masterDataSource
        dataSource.setDefaultTargetDataSource(masterDataSource);

        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(ds);
        /** add mybatis config file */
        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        /** add mybatis mapper file */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/**/*.xml"));
            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("load mapper config error ,error is :{}", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("return SqlSessionFactory is error ,error is :{}", e);
            e.printStackTrace();
        }
        return null;
    }


    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        return servletRegistrationBean;
    }

    @Bean
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        statFilter.setSlowSqlMillis(1000);

        return statFilter;
    }

    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        //允许执行多条SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.xqsight.**.service.*.*(..))";

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor ti = new TransactionInterceptor();
        ti.setTransactionManagerBeanName("transactionManager");
        Properties properties = new Properties();

        properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("search*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
        properties.setProperty("save*", "PROPAGATION_REQUIRED");
        properties.setProperty("add*", "PROPAGATION_REQUIRED");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
        properties.setProperty("remove*", "PROPAGATION_REQUIRED");
        properties.setProperty("del*", "PROPAGATION_REQUIRED");
        properties.setProperty("update*", "PROPAGATION_REQUIRED");
        properties.setProperty("edit*", "PROPAGATION_REQUIRED");
        properties.setProperty("call*", "PROPAGATION_REQUIRED");
        properties.setProperty("batch*", "PROPAGATION_REQUIRED");
        properties.setProperty("dealWith*", "PROPAGATION_REQUIRED");
        properties.setProperty("inIsolate*", "PROPAGATION_REQUIRED");
        properties.setProperty("*", "PROPAGATION_SUPPORTS");
        ti.setTransactionAttributes(properties);

        MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        source.setTransactionAttribute(transactionAttribute);

        ti.setTransactionAttributeSource(source);
        return ti;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor());
    }
}
