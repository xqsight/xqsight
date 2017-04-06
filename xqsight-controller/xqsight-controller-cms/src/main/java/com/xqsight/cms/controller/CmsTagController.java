/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.service.CmsTagService;

/**
 * <p>标签表 controller</p>
 * <p>Table: cms_tag - 标签表</p>
 * @since 2017-04-06 02:35:12
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/tag")
public class CmsTagController extends BaseController<CmsTagService,CmsTag,Long> {

}