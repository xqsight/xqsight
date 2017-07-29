package com.xqsight.data.ehcache.core;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017年07月28日 下午11:14
 */
@Component
public class CacheTemplate {

    private Logger logger = LogManager.getLogger(CacheTemplate.class);

    @Autowired
    private CacheManager cacheManager;

    private static final String SYS_CACHE = "sysCache";

    public Object get(String key) {
        return get(SYS_CACHE, key);
    }

    public Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    public Object get(String cacheName, String key) {
        Element element = getCache(cacheName).get(getKey(key));
        return element == null ? element : element.getObjectValue();
    }

    public Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    public void put(String key, Object value) {
        put(SYS_CACHE, key, value,null);
    }

    public void put(String key,Object value,Integer seconds){
        put(SYS_CACHE,key,value,seconds);
    }

    public void put(String cacheName, String key, Object value,Integer seconds) {
        Element element = new Element(key, value);
        if(seconds != null && seconds > 0){
            element.setTimeToLive(seconds);
        }
        getCache(cacheName).put(element);
    }

    public void remove(String key) {
        remove(SYS_CACHE, key);
    }

    public void remove(String cacheName, String key) {
        getCache(cacheName).remove(getKey(key));
    }

    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        List keys = cache.getKeys();
        for (Object key : keys) {
            cache.remove(key);
        }
        logger.info("清理缓存： {} => {}", cacheName, keys);
    }

    private String getKey(String key) {
        /*String dsName = DataSourceHolder.getDataSourceName();
		if (StringUtils.isNotBlank(dsName)){
			return dsName + "_" + key;
		}*/
        return key;
    }

    private Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }

}
