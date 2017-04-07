/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.cms.support.CmsGenerateService;
import com.xqsight.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>广告表 controller</p>
 * <p>Table: cms_ad - 广告表</p>
 *
 * @author wangganggang
 * @since 2017-04-06 02:34:40
 */
@RestController
@RequestMapping("/cms/ad")
public class CmsAdController extends BaseController<CmsAdService, CmsAd, Long> {

    @Autowired
    private CmsGenerateService cmsGenerateService;

    @Override
    protected void afterPut(CmsAd cmsAd) throws Exception {
        cmsGenerateService.generateIndex();
    }

    @Override
    protected void afterDelete(Long id) throws Exception {
        cmsGenerateService.generateIndex();
    }
}