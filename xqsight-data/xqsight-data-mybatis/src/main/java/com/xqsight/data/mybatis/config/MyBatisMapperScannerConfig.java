package com.xqsight.data.mybatis.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis扫描配置
 * @author seven sins
 *
 */
@Configuration
@AutoConfigureAfter(MybatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        /** mapper package、xml namespace **/
        mapperScannerConfigurer.setBasePackage("com.xqsight.**.mapper");

        return mapperScannerConfigurer;
    }
}