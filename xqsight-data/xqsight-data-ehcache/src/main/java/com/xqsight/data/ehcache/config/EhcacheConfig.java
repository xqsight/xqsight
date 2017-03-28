package com.xqsight.data.ehcache.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Configuration
@EnableCaching
public class EhcacheConfig {

    private Logger logger = LogManager.getLogger(EhcacheConfig.class);

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        logger.debug("CacheConfiguration.ehCacheManagerFactoryBean()");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("data-ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean cacheManagerFactoryBean) {
        logger.debug("CacheConfiguration.ehCacheCacheManager()");
        return new EhCacheCacheManager(cacheManagerFactoryBean.getObject());
    }
}
