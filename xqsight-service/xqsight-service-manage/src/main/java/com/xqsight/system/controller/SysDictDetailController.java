/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.support.MessageSupport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.system.model.SysDictDetail;
import com.xqsight.system.service.SysDictDetailService;

/**
 * <p>系统字典明细表 controller</p>
 * <p>Table: sys_dict_detail - 系统字典明细表</p>
 * @since 2017-01-05 06:10:32
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/dict/detail/")
public class SysDictDetailController{

	@Autowired
	private SysDictDetailService sysDictDetailService;

	@RequestMapping("save")
	@RequiresPermissions("sys:dict:detail:save")
	public Object save(SysDictDetail sysDictDetail) {
		sysDictDetailService.save(sysDictDetail);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:dict:detail:update")
	public Object update(SysDictDetail sysDictDetail) {
		sysDictDetailService.update(sysDictDetail, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:dict:detail:delete")
	public Object delete(Long dictDetailId) {
		sysDictDetailService.delete(dictDetailId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:dict:detail:delete")
	public Object logicDel(Long dictDetailId) {
		sysDictDetailService.logicDel(dictDetailId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:dict:detail:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysDictDetail> sysDictDetails = sysDictDetailService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysDictDetails);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:dict:detail:query")
	public Object queryById(Long dictDetailId) {
		SysDictDetail sysDictDetail = sysDictDetailService.get(dictDetailId);
		return MessageSupport.successDataMsg(sysDictDetail, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:dict:detail:query")
	public Object queryall() {
		List<SysDictDetail> sysDictDetails = sysDictDetailService.search(null);
		return MessageSupport.successDataMsg(sysDictDetails, "查询成功");
    }

}