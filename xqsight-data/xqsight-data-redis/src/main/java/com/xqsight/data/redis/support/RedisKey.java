package com.xqsight.data.redis.support;

/**
 * @author wangganggang
 * @date 2017年07月24日 10:49
 */
public class RedisKey {
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}