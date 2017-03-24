package com.xqsight.data.mybatis.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author wangganggang
 * @date 2017/03/24
 */

@Configuration
@AutoConfigureAfter(DruidConfiguration.class)
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

    @Resource
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // bean.setTypeAliasesPackage("com.demo.domain"); Mapper.xml中类型为类的全名， 可不配置此属性

        /** 分页插件 **/
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect","mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        /** 添加插件 **/
        bean.setPlugins(new Interceptor[]{pageHelper});


        /** 添加XML目录 */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务
     */
    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor ti = new TransactionInterceptor();
        ti.setTransactionManager(annotationDrivenTransactionManager());
        Properties properties = new Properties();

        properties.setProperty("find*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("get*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("search*", "PROPAGATION_REQUIRED, readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED");
        properties.setProperty("save*", "PROPAGATION_REQUIRED");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
        properties.setProperty("del*", "PROPAGATION_REQUIRED");
        properties.setProperty("update*", "PROPAGATION_REQUIRED");
        ti.setTransactionAttributes(properties);

        return ti;
    }

    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(false);
        transactionAutoProxy.setBeanNames(new String[]{"*ServiceImpl"});
        transactionAutoProxy.setInterceptorNames(new String[]{"transactionInterceptor"});

        return transactionAutoProxy;
    }
}