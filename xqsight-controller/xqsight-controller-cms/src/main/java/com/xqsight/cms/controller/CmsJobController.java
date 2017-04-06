/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsJob;
import com.xqsight.cms.service.CmsJobService;
import com.xqsight.cms.support.CmsGenerateService;
import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.freemarker.TemplateEngineException;
import com.xqsight.common.model.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>招聘表 controller</p>
 * <p>Table: cms_job - 招聘表</p>
 * @since 2017-04-06 02:34:52
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/job")
public class CmsJobController extends BaseController<CmsJobService,CmsJob,Long> {

    @Autowired
    private CmsGenerateService cmsGenerateService;

    @Override
    public Object save(CmsJob cmsJob) {
        service.add(cmsJob);
        try {
            cmsGenerateService.generateJob();
        } catch (TemplateEngineException e) {
            e.printStackTrace();
        }
        return new BaseResult();
    }

    @Override
    public Object update(CmsJob cmsJob) {
        service.editById(cmsJob);
        try {
            cmsGenerateService.generateJob();
        } catch (TemplateEngineException e) {
            e.printStackTrace();
        }
        return new BaseResult();
    }

    @Override
    public Object deleteById(@PathVariable Long id) {
        service.removeById(id);
        try {
            cmsGenerateService.generateJob();
        } catch (TemplateEngineException e) {
            e.printStackTrace();
        }
        return new BaseResult();
    }
}