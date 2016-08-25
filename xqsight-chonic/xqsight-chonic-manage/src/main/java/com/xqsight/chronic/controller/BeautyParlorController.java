/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.BeautyParlor;
import com.xqsight.chronic.service.BeautyParlorService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.utils.MapKeyHandle;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * <p>美容院表 controller</p>
 * <p>Table: BEAUTY_PARLOR - 美容院表</p>
 *
 * @since 2016-05-09 07:48:43
 */
@RestController
@RequestMapping("/beauty/parlor/")
public class BeautyParlorController {

    @Autowired
    private BeautyParlorService beautyParlorService;

    @RequestMapping("save")
    public Object saveBeautyParlor(HttpServletRequest request, BeautyParlor beautyParlor) {
        if (!WebUtils.isMobile(request))
            beautyParlor.setCreateOprId(SSOUtils.getCurrentUserId().toString());

        beautyParlorService.saveBeautyParlor(beautyParlor);
        return MessageSupport.successMsg("保存成功");

    }

    @RequestMapping("update")
    public Object updateBeautyParlor(HttpServletRequest request, BeautyParlor beautyParlor) {
        if (!WebUtils.isMobile(request))
            beautyParlor.setUpdOprId(SSOUtils.getCurrentUserId().toString());

        beautyParlorService.updateBeautyParlor(beautyParlor);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object deleteBeautyParlor(Long beautyId) {
        beautyParlorService.deleteBeautyParlor(beautyId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object queryBeautyParlor(XqsightPage xqsightPage) {
        Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<BeautyParlor> beautyParlors = beautyParlorService.queryBeautyParlor();
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, beautyParlors);
    }

    @RequestMapping("querybyid")
    public Object queryBeautyParlor(Long beautyId) {
        BeautyParlor beautyParlor = beautyParlorService.queryBeautyParlorById(beautyId);
        return MessageSupport.successDataMsg(beautyParlor, "查询成功");
    }

    @RequestMapping("querynear")
    public Object queryNearBeautyParlor(XqsightPage xqsightPage, BeautyParlor beautyParlor) {
        Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<Map<String, Object>> beautyParlors = beautyParlorService.queryBeautyParlorWithFirstPic(beautyParlor.getBeautyAddress());
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(beautyParlors));
    }

}