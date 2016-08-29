/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xqsight.sys.support;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.cache.CacheKeyConstants;
import com.xqsight.commons.web.SpringContextHolder;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.service.SysDictService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * @author jerry
 * @version 2013-5-29
 */
public class DictSupport {
	
	private static SysDictService sysDictService = SpringContextHolder.getBean(SysDictService.class);

	public static String getDictDetailValue(String dictValue, String dictCode, String defaultValue){
		if (StringUtils.isNotBlank(dictCode) && StringUtils.isNotBlank(dictValue)){
			return MapUtils.getString(getDictList(dictCode),dictValue,defaultValue);
		}
		return defaultValue;
	}

	public static String getDictDetailValue(String dictValue, String dictCode){
		return getDictDetailValue(dictValue,dictCode,"");
	}

	public static Map<String,String> getDictList(String dictCode){
		Map<String, Map<String,String>> dictMap = (Map<String, Map<String,String>>) CacheTemplate.get(CacheKeyConstants.SYS_DICT_MAP);
		if(dictMap == null){
			List<SysDict> sysDicts = sysDictService.querySysDictByDictName("");
			for (SysDict sysDict :sysDicts ){
				List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(sysDict.getDictId());
				Map<String,String> detailMap = new HashMap<>();
				for(SysDictDetail sysDictDetail : sysDictDetails){
					detailMap.put(sysDictDetail.getDictValue(),sysDictDetail.getDictDesp());
				}
				dictMap.put(sysDict.getDictCode(),detailMap);
			}
			CacheTemplate.put(CacheKeyConstants.SYS_DICT_MAP, dictMap);
		}

		Map<String,String> detailDictMap = dictMap.get(dictCode);
		if (detailDictMap == null){
			detailDictMap = new HashMap<>();
		}
		return detailDictMap;
	}

	/**
	 * 返回字典列表（JSON）
	 * @param dictCode
	 * @return
	 */
	public static String getDictDetailListJson(String dictCode){
		return JSON.toJSONString(getDictList(dictCode));
	}
	
}
