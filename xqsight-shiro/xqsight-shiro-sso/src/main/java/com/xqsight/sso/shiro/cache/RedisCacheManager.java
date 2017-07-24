package com.xqsight.sso.shiro.cache;
///**
//package com.xqsight.sso.shiro.cache;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import org.apache.common.logging.Log;
//import org.apache.common.logging.LogFactory;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.cache.CacheManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.template.RedisTemplate;
//
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
