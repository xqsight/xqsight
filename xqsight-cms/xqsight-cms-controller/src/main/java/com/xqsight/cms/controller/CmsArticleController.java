/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.cms.support.CmsGenerateService;
import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.model.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>文章表 controller</p>
 * <p>Table: cms_article - 文章表</p>
 *
 * @author wangganggang
 * @since 2017-04-06 02:34:47
 */
@RestController
@RequestMapping("/cms/article")
public class CmsArticleController extends BaseController<CmsArticleService, CmsArticle, Long> {
    @Autowired
    private CmsGenerateService cmsGenerateService;

    @Override
    protected void afterPut(CmsArticle cmsArticle) {
        service.saveArticleTag(cmsArticle);
        afterOperate(cmsArticle);
    }

    @Override
    protected void afterDelete(CmsArticle cmsArticle) {
        cmsGenerateService.generateIndex();
    }

    private void afterOperate(CmsArticle cmsArticle) {
        cmsGenerateService.generateArticle(cmsArticle);
        cmsGenerateService.generateIndex();
    }

    @Override
    public Object getById(@PathVariable Long id) {
        CmsArticle cmsArticle = service.getById(id);
        cmsArticle.setTags(service.queryArticleTag(id));
        return new BaseResult(cmsArticle);
    }
}