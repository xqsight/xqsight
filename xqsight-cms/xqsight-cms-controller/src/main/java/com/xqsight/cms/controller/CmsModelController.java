/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.common.base.controller.BaseTreeController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>模块表 controller</p>
 * <p>Table: cms_model - 模块表</p>
 * @since 2017-04-06 02:34:57
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/model")
public class CmsModelController extends BaseTreeController<CmsModelService,CmsModel,Long> {

}