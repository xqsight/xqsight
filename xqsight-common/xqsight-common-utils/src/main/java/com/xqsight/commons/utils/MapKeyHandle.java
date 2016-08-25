/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.xiaoleilu.hutool.util.StrUtil;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年5月12日 上午10:34:55
 */
public class MapKeyHandle {

    /**
     * map 的 key 转成 java命名方式
     *
     * @param @param  map
     * @param @return 设定文件
     * @return Map<String,Object>    返回类型
     * @throws
     * @Description: TODO
     * @Title: keyToJavaProperty
     */
    public static Map<String, Object> keyToJavaProperty(Map<String, Object> map) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if (map == null)
            return null;

        for (String key : map.keySet()) {
            String newKey = StrUtil.lowerFirst(StrUtil.toCamelCase(key));
            retMap.put(newKey, MapUtils.getObject(map, key));
        }
        return retMap;
    }

    /**
     * map 的 key 转成 java命名方式
     *
     * @param @param  listMap
     * @param @return 设定文件
     * @return List<Map<String,Object>>    返回类型
     * @throws
     * @Description: TODO
     * @Title: keyToJavaProperty
     */
    public static List<Map<String, Object>> keyToJavaProperty(List<Map<String, Object>> listMap) {
        List<Map<String, Object>> newListMap = new ArrayList<Map<String, Object>>();
        if (listMap == null)
            return null;

        for (Map<String, Object> map : listMap) {
            newListMap.add(keyToJavaProperty(map));
        }
        return newListMap;
    }
}
