package com.xqsight.data.ehcache.config;

import net.sf.ehcache.CacheManager;
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
    public EhCacheManagerFactoryBean ehcache() {
        logger.debug("ehcache factory init ");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("data-ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

    @Bean(name = "cacheManager")
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        logger.debug("cacheManage init");
        return new EhCacheCacheManager(cacheManager);
    }

}
