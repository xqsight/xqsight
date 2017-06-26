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
 * Cache工具类
 *
 * @version 2013-5-29
 */
@Component
public class CacheTemplate {

    private Logger logger = LogManager.getLogger(CacheTemplate.class);

    @Autowired
    private CacheManager cacheManager;

    private static final String SYS_CACHE = "sysCache";

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return get(SYS_CACHE, key);
    }

    /**
     * 获取SYS_CACHE缓存
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String key, Object defaultValue) {
        Object value = get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 写入SYS_CACHE缓存
     *
     * @param key
     * @return
     */
    public void put(String key, Object value) {
        put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     *
     * @param key
     * @return
     */
    public void remove(String key) {
        remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        return getCache(cacheName).get(getKey(key));
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @param defaultValue
     * @return
     */
    public Object get(String cacheName, String key, Object defaultValue) {
        Object value = get(cacheName, getKey(key));
        return value != null ? value : defaultValue;
    }

    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        Element element = new Element(key,value);
        getCache(cacheName).put(element);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public void remove(String cacheName, String key) {
        getCache(cacheName).remove(getKey(key));
    }

    /**
     * 从缓存中移除所有
     *
     * @param cacheName
     */
    public void removeAll(String cacheName) {
        Cache cache = getCache(cacheName);
        List keys = cache.getKeys();
        for (Object key : keys) {
            cache.remove(key);
        }
        logger.info("清理缓存： {} => {}", cacheName, keys);
    }

    /**
     * 获取缓存键名，多数据源下增加数据源名称前缀
     *
     * @param key
     * @return
     */
    private String getKey(String key) {
//		String dsName = DataSourceHolder.getDataSourceName();
//		if (StringUtils.isNotBlank(dsName)){
//			return dsName + "_" + key;
//		}
        return key;
    }

    /**
     * 获得一个Cache，没有则显示日志。
     *
     * @param cacheName
     * @return
     */
    private Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            throw new RuntimeException("当前系统中没有定义“" + cacheName + "”这个缓存。");
        }
        return cache;
    }

}
