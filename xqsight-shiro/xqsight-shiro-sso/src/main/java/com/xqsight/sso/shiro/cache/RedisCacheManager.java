package com.xqsight.sso.shiro.cache;///**
// * 上海汽车集团财务有限责任公司
// * Copyright (c) 1994-2015 All Rights Reserved.
// */
//package com.xqsight.sso.shiro.cache;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.cache.CacheManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * 
// * @author linhaoran
// * @version RedisCacheManager.java, v 0.1 2015年7月3日 上午10:08:16 linhaoran
// */
//public class RedisCacheManager implements CacheManager {
//    
//    private Log logger = LogFactory.getLog(getClass());
//    
// // fast lookup by name map
//    @SuppressWarnings("rawtypes")
//    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
//
//    @SuppressWarnings("rawtypes")
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * The Redis key prefix for caches 
//     */
//    private String keyPrefix = "sro-c:";
//    
//    /**
//     * Returns the Redis session keys
//     * prefix.
//     * @return The prefix
//     */
//    public String getKeyPrefix() {
//        return keyPrefix;
//    }
//
//    /**
//     * Sets the Redis sessions key 
//     * prefix.
//     * @param keyPrefix The prefix
//     */
//    public void setKeyPrefix(String keyPrefix) {
//        this.keyPrefix = keyPrefix;
//    }
//    
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Override
//    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
//        logger.debug("获取名称为: " + name + " 的RedisCache实例");
//        
//        Cache c = caches.get(name);
//        
//        if (c == null) {
//            // create a new cache instance
//            c = new RedisStringKeyCache<K, V>(redisTemplate, keyPrefix);
//            
//            // add it to the cache collection
//            caches.put(name, c);
//        }
//        return c;
//    }
//
//}
