/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.wechat.bxs.model.WxPromise;
import com.xqsight.wechat.bxs.service.PromiseService;
import com.xqsight.wechat.bxs.service.WxUserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description: this is use for
 * @author xqsight-jerry
 * @date 2016年3月26日 下午6:50:28
 */
@Controller
@RequestMapping("/promise/bxs/")
public class PromiseController {

	private static Logger logger = LogManager.getLogger(PromiseController.class);

	@Autowired
	private PromiseService promiseService;
	
	@Autowired
	private WxUserInfoService wxUserInfoService;

	@RequestMapping("save")
	@ResponseBody
	private Object save(WxPromise wxPromise) {
		logger.debug("传入的参数wxPromise:{}", JSON.toJSONString(wxPromise));
		wxUserInfoService.updateBase(wxPromise);
		promiseService.save(wxPromise);
		return MessageSupport.successMsg("保存成功");
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(long promiseId) {
		logger.debug("传入的参数promiseId:{}", promiseId);
		promiseService.delete(promiseId);
		return MessageSupport.successMsg("删除成功");
	}

	
	@RequestMapping("cancel")
	@ResponseBody
	public Object cancel(WxPromise wxPromise) {
		logger.debug("传入的参数wxPromise:{}", JSON.toJSONString(wxPromise));
		promiseService.updateStatus(wxPromise);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("query")
	@ResponseBody
	public Object query(XqsightPage xqsightPage,WxPromise wxPromise) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<WxPromise> wxPromises = promiseService.query();
		xqsightPage.setTotalCount(page.getTotal());
		return  MessageSupport.successDataTableMsg(xqsightPage,wxPromises);
	}
	
	@RequestMapping("querybywxusercode")
	@ResponseBody
	public Object queryByWxUserCode(XqsightPage xqsightPage, WxPromise wxPromise) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<WxPromise> wxPromises = promiseService.queryByUserId(wxPromise.getWxUserId());
		xqsightPage.setTotalCount(page.getTotal());
		return  MessageSupport.successDataTableMsg(xqsightPage,wxPromises);
	}
	
	@RequestMapping("querybyid")
	@ResponseBody
	public Object queryByPromiseIdCode(HttpServletRequest request,long promiseId) {
		WxPromise wxPromise = promiseService.querybyId(promiseId);
		return MessageSupport.successDataMsg(wxPromise,"查询成功");
	}
}
