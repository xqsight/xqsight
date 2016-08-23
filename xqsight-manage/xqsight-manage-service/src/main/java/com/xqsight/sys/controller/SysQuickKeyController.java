/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysQuickKey;
import com.xqsight.sys.service.SysQuickKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/quickkey/")
public class SysQuickKeyController{

	@Autowired
	private SysQuickKeyService sysQuickKeyService;

	@RequestMapping("save")
	public Object saveRole(HttpServletRequest request) {
		String[] funOrders = request.getParameterValues("funOrder");
		String[] keyIcons = request.getParameterValues("keyIcon");
		String[] keyTitles = request.getParameterValues("keyTitle");
		String[] keyValues = request.getParameterValues("keyValue");
		long id = SSOUtils.getCurrentUserId();
		List<SysQuickKey> sysQuickKeys = new ArrayList<SysQuickKey>();
		for(int i = 0;i < funOrders.length; i++){
			SysQuickKey sysQuickKey = new SysQuickKey();
			sysQuickKey.setCreateOprId("" + id);
			sysQuickKey.setId(id);
			sysQuickKey.setFunOrder(Integer.valueOf(funOrders[i]));
			sysQuickKey.setKeyIcon(keyIcons[i]);
			sysQuickKey.setKeyTitle(keyTitles[i]);
			sysQuickKey.setKeyValue(keyValues[i]);
			sysQuickKeys.add(sysQuickKey);
		}
		sysQuickKeyService.saveSysQuickKey(id,sysQuickKeys);
		return MessageSupport.successMsg("保存成功");
	}

	@RequestMapping("querybyid")
	public Object queryRoleById() {
		List<SysQuickKey> sysQuickKeys = sysQuickKeyService.querySysQuickKeyById(SSOUtils.getCurrentUserId());
		return MessageSupport.successDataMsg(sysQuickKeys, "查询成功");
	}
}
