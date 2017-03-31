/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.common.utils;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年5月12日 上午10:34:55
 */
public class MapKeyHandle {

    /** map 的 key 转成 java命名方式 **/
    public static Map keyToJavaProperty(Map map) {
        Map retMap = new HashMap();
        if (map == null){ return null;}

        for (Object key : map.keySet()) {
            String newKey = StringUtils.lowerFirst(StringUtils.toCamelCase(key.toString()));
            retMap.put(newKey, MapUtils.getObject(map, key));
        }
        return retMap;
    }

    /** map 的 key 转成 java命名方式 **/
    public static List<Map> keyToJavaProperty(List<Map> listMap) {
        List<Map> newListMap = new ArrayList<Map>();
        if (listMap == null) {return null;}

        for (Map<String, Object> map : listMap) {
            newListMap.add(keyToJavaProperty(map));
        }
        return newListMap;
    }


}
