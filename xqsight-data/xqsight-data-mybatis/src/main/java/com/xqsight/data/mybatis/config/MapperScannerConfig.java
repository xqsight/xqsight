package com.xqsight.data.mybatis.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@Configuration
@AutoConfigureAfter({SessionFactoryConfig.class,TransactionManage.class})
public class MapperScannerConfig {

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.xqsight.**.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

//    @Bean(name = "mapperScannerConfigurerBatch")
//    public MapperScannerConfigurer mapperScannerConfigurerBatch(){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("com.xqsight.**.mapper.batch");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionTemplateBatch");
//        mapperScannerConfigurer.setAnnotationClass(BatchAnnotation.class);
//        return mapperScannerConfigurer;
//    }
}
