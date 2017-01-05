package com.xqsight.sso.shiro.cache;///**
//package com.xqsight.sso.shiro.cache;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.shiro.cache.Cache;
//import org.apache.shiro.cache.CacheException;
//import org.springframework.data.redis.core.RedisTemplate;
//
//public class RedisStringKeyCache<K, V> implements Cache<String, V> {
//
//    private Logger logger = LogManager.getLogger(getClass());
//    
////    private FastJsonRedisTemplate fastJsonRedisTemplate;
//    
//    private RedisTemplate<String, V> redisTemplate;
//    
//    /**
//     * The Redis key prefix for the sessions 
//     */
//    private String keyPrefix = null;
//    
//    /**
//     * Setter method for property <tt>redisTemplate</tt>.
//     * 
//     * @param redisTemplate value to be assigned to property redisTemplate
//     */
//    public void setRedisTemplate(RedisTemplate<String, V> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
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
//    @SuppressWarnings("unused")
//    private RedisStringKeyCache(){}
//    
//    /**
//     * Constructs a cache instance with the specified
//     * Redis manager and using a custom key prefix.
//     * @param cache The cache manager instance
//     * @param prefix The Redis key prefix
//     */
//    public RedisStringKeyCache(RedisTemplate<String, V> redisTemplate, 
//                String prefix){
//         
//        this.setRedisTemplate(redisTemplate);
//        // set the prefix
//        this.keyPrefix = prefix;
//    }
//    
//    /**
//     * 获得byte[]型的key
//     * @param key
//     * @return
//     */
//    private String getRedisKey(String key){
//        return this.keyPrefix + key;
//    }
//    
//    @Override
//    public V get(String key) throws CacheException {
//        logger.debug("根据key从Redis中获取对象 key [{}]", key);
//        try {
//            if (key == null) {
//                return null;
//            }else{
//                V v = redisTemplate.opsForValue().get(getRedisKey(key));
//                return v;
//            }
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//
//    }
//
//    @Override
//    public V put(String key, V value) throws CacheException {
//        logger.debug("根据key从存储 key [{}]", key);
//         try {
//                redisTemplate.opsForValue().set(getRedisKey(key), value);
//                return value;
//            } catch (Throwable t) {
//                throw new CacheException(t);
//            }
//    }
//
//    @Override
//    public V remove(String key) throws CacheException {
//        logger.debug("从redis中删除 key [{}]", key);
//        try {
//            String redisKey = getRedisKey(key);
//            V previous = get(redisKey);
//            redisTemplate.delete(redisKey);
//            return previous;
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//    }
//
//    @Override
//    public void clear() throws CacheException {
//        logger.debug("从redis中删除所有元素");
//        try {
//            redisTemplate.delete(redisTemplate.keys(keyPrefix + "*"));
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
//    }
//
//    @Override
//    public int size() {
//        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
//        return keys == null ? 0 : keys.size();
//    }
//
//    @Override
//    public Set<String> keys() {
//        return redisTemplate.keys(keyPrefix + "*");
//    }
//
//    @Override
//    public Collection<V> values() {
//        List<V> list = redisTemplate.opsForValue().multiGet(redisTemplate.keys(keyPrefix + "*"));
//        if(list == null){
//            return Collections.emptyList();
//        }
//        return Collections.unmodifiableList(list);
//    }
//}
