/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xqsight.sys.support;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.cache.CacheKeyConstants;
import com.xqsight.commons.support.CacheUtils;
import com.xqsight.commons.web.SpringContextHolder;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.service.SysDictService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
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
			for (SysDictDetail sysDictDetail : getDictList(dictCode)){
				if (dictValue.equals(sysDictDetail.getDictValue())){
					return sysDictDetail.getDictDesp();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictDetailValue(String dictValue, String dictCode){
		return getDictDetailValue(dictValue,dictCode,"");
	}

	public static List<SysDictDetail> getDictList(String dictCode){
		Map<String, List<SysDictDetail>> dictMap = (Map<String, List<SysDictDetail>>) CacheUtils.get(CacheKeyConstants.SYS_DICT_MAP);
		if(dictMap == null){
			List<SysDict> sysDicts = sysDictService.querySysDictByDictName("");
			for (SysDict sysDict :sysDicts ){
				List<SysDictDetail> sysDictDetails = sysDictService.querySysDictDetailByDictId(sysDict.getDictId());
				dictMap.put(sysDict.getDictCode(),sysDictDetails);
			}
			CacheUtils.put(CacheKeyConstants.SYS_DICT_MAP, dictMap);
		}

		List<SysDictDetail> dictList = dictMap.get(dictCode);
		if (dictList == null){
			dictList = new ArrayList();
		}
		return dictList;
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
