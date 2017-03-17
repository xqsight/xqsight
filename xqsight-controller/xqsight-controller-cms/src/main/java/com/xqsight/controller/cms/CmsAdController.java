/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.cms.service.CmsGenerateService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.freemarker.TemplateEngineException;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;

/**
 * <p>广告表 controller</p>
 * <p>Table: cms_ad - 广告表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:51:56
 */
@RestController
@RequestMapping("/cms/ad/")
public class CmsAdController {

    @Autowired
    private CmsAdService cmsAdService;

    @Autowired
    private CmsGenerateService cmsGenerateService;

    @RequestMapping("save")
    public Object save(CmsAd cmsAd) {
        cmsAdService.save(cmsAd, true);
        try {
            cmsGenerateService.generateIndex();
        } catch (TemplateEngineException e) {
            e.printStackTrace();
        }
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(CmsAd cmsAd) {
        cmsAdService.update(cmsAd, true);

        try {
            cmsGenerateService.generateIndex();
        } catch (TemplateEngineException e) {
            e.printStackTrace();
        }
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long adId) {
        cmsAdService.delete(adId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long adId) {
        cmsAdService.logicDel(adId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(XqsightPage xqsightPage, String type) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        PropertyFilterBuilder propertyFilterBuilder = PropertyFilterBuilder.create();
        if (StringUtils.isNotBlank(type)) {
            propertyFilterBuilder.matchTye(MatchType.EQ).propertyType(PropertyType.I).add("type", type);
        } else {
            propertyFilterBuilder.matchTye(MatchType.GE).propertyType(PropertyType.I).add("type", "2");
        }
        List<CmsAd> cmsAds = cmsAdService.search(propertyFilterBuilder.end());
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, cmsAds);
    }

    @RequestMapping("querybyid")
    public Object queryById(Long adId) {
        CmsAd cmsAd = cmsAdService.get(adId);
        return MessageSupport.successDataMsg(cmsAd, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<CmsAd> cmsAds = cmsAdService.search(null);
        return MessageSupport.successDataMsg(cmsAds, "查询成功");
    }

}