/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.cms.model.CmsJob;
import com.xqsight.cms.service.CmsJobService;

/**
 * <p>招聘表 controller</p>
 * <p>Table: cms_job - 招聘表</p>
 * @since 2017-02-23 04:52:11
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/job/")
public class CmsJobController{

	@Autowired
	private CmsJobService cmsJobService;

	@RequestMapping("save")
	public Object save(CmsJob cmsJob) {
		cmsJobService.save(cmsJob,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object update(CmsJob cmsJob) {
		cmsJobService.update(cmsJob, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object delete(Long jobId) {
		cmsJobService.delete(jobId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	public Object logicDel(Long jobId) {
		cmsJobService.logicDel(jobId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsJob> cmsJobs = cmsJobService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsJobs);
    }

	@RequestMapping("querybyid")
	public Object queryById(Long jobId) {
		CmsJob cmsJob = cmsJobService.get(jobId);
		return MessageSupport.successDataMsg(cmsJob, "查询成功");
	}

	@RequestMapping("queryall")
	public Object queryall() {
		List<CmsJob> cmsJobs = cmsJobService.search(null);
		return MessageSupport.successDataMsg(cmsJobs, "查询成功");
    }

}