package com.xqsight.data.ehcache.support;

/**
 * @author wangganggang
 * @date 2017年07月24日 11:20
 */
public class EhcacheKey {
    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
