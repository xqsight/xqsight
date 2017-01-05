/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.orm.MatchType;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.PropertyType;
import com.xqsight.common.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.xqsight.system.model.SysQuickKey;
import com.xqsight.system.service.SysQuickKeyService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>快捷键表 controller</p>
 * <p>Table: sys_quick_key - 快捷键表</p>
 * @since 2017-01-05 06:11:05
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/quick/key/")
public class SysQuickKeyController{

	@Autowired
	private SysQuickKeyService sysQuickKeyService;

	@RequestMapping("save")
	public Object save(HttpServletRequest request) {
		String[] funOrders = request.getParameterValues("funOrder");
		String[] keyIcons = request.getParameterValues("keyIcon");
		String[] keyTitles = request.getParameterValues("keyTitle");
		String[] keyValues = request.getParameterValues("keyValue");
		long id = SSOUtils.getCurrentUserId();
		List<SysQuickKey> sysQuickKeys = new ArrayList<SysQuickKey>();
		for(int i = 0;i < funOrders.length; i++){
			SysQuickKey sysQuickKey = new SysQuickKey();
			sysQuickKey.setCreateUserId("" + id);
			sysQuickKey.setId(id);
			sysQuickKey.setFunOrder(Integer.valueOf(funOrders[i]));
			sysQuickKey.setKeyIcon(keyIcons[i]);
			sysQuickKey.setKeyTitle(keyTitles[i]);
			sysQuickKey.setKeyValue(keyValues[i]);
			sysQuickKeys.add(sysQuickKey);
		}
		sysQuickKeyService.save(id,sysQuickKeys);
		return MessageSupport.successMsg("保存成功");
	}

	@RequestMapping("query")
	public Object query(Long currentUserId) {
		List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ).propertyType(PropertyType.I)
				.add("id","" + currentUserId).end();
		List<SysQuickKey> sysQuickKeys = sysQuickKeyService.search(propertyFilters);
		return MessageSupport.successDataMsg(sysQuickKeys, "查询成功");
	}

}