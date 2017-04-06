/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsSite;
import com.xqsight.cms.service.CmsSiteService;
import com.xqsight.common.base.controller.BaseTreeController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>站点表 controller</p>
 * <p>Table: cms_site - 站点表</p>
 * @since 2017-04-06 02:35:07
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/site")
public class CmsSiteController extends BaseTreeController<CmsSiteService,CmsSite,Long> {

}