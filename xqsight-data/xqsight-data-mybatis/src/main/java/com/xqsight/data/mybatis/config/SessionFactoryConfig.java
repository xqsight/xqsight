package com.xqsight.data.mybatis.config;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@Configuration
public class SessionFactoryConfig {

    protected Logger logger = LogManager.getLogger(SessionFactoryConfig.class);

    @Resource(name = "commonDataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /** add mybatis config file */
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        /** add mybatis mapper file */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/**/*.xml"));
            return bean.getObject();
        } catch (IOException e) {
            logger.error("load mapper config error ,error is :{}", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("return SqlSessionFactory is error ,error is :{}", e);
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "sqlSessionFactoryBatch")
    public SqlSessionFactory sqlSessionFactoryBeanBatch() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /** add mybatis mapper file */
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/batch/**/*.xml"));
            return bean.getObject();
        } catch (IOException e) {
            logger.error("load mapper config error ,error is :{}", e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("return SqlSessionFactory is error ,error is :{}", e);
            e.printStackTrace();
        }
        return null;
    }

    @Bean(name = "sqlSessionTemplateBatch")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactoryBatch) {
        return new SqlSessionTemplate(sqlSessionFactoryBatch, ExecutorType.BATCH);
    }


}
