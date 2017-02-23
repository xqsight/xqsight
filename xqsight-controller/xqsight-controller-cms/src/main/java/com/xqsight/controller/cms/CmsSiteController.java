/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.cms.model.CmsPosition;
import com.xqsight.cms.model.CmsSite;
import com.xqsight.cms.service.CmsSiteService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>站点表 controller</p>
 * <p>Table: cms_site - 站点表</p>
 * @since 2017-02-23 04:52:30
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/site/")
public class CmsSiteController{

	@Autowired
	private CmsSiteService cmsSiteService;

	@RequestMapping("save")
	public Object save(CmsSite cmsSite) {
		if (StringUtils.equals(cmsSite.getParentId(), "0")) {
			cmsSite.setParentIds(",0,");
		} else {
			CmsSite parentSite = cmsSiteService.get(Long.valueOf(cmsSite.getParentId()));
			cmsSite.setParentIds(parentSite.getParentIds() + parentSite.getSiteId() + Constants.COMMA_SIGN_SPLIT_NAME);
		}
		cmsSiteService.save(cmsSite,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object update(CmsSite cmsSite) {
		cmsSiteService.update(cmsSite, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object delete(Long siteId) {
		cmsSiteService.delete(siteId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	public Object logicDel(Long siteId) {
		cmsSiteService.logicDel(siteId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	public Object query(String parentId) {
		List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
				.propertyType(PropertyType.L).add("parent_id", parentId).end();
		List<CmsSite> cmsSites = cmsSiteService.search(propertyFilters);
		return MessageSupport.successDataMsg(cmsSites, "查询成功");
    }

	@RequestMapping("querybyid")
	public Object queryById(Long siteId) {
		CmsSite cmsSite = cmsSiteService.get(siteId);
		return MessageSupport.successDataMsg(cmsSite, "查询成功");
	}

	@RequestMapping("queryall")
	public Object queryall() {
		List<CmsSite> cmsSites = cmsSiteService.search(null);
		return MessageSupport.successDataMsg(cmsSites, "查询成功");
    }

	@RequestMapping("querytree")
	public Object queryTree() {
		List<CmsSite> cmsSites = cmsSiteService.search(null);
		cmsSites = new TreeSupport<CmsSite>().generateTree(cmsSites);
		return MessageSupport.successDataMsg(cmsSites, "查询成功");
	}

}