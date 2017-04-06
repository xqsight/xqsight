/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsPosition;
import com.xqsight.cms.service.CmsPositionService;
import com.xqsight.common.base.controller.BaseTreeController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>职位表 controller</p>
 * <p>Table: cms_position - 职位表</p>
 * @since 2017-04-06 02:35:02
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/position")
public class CmsPositionController extends BaseTreeController<CmsPositionService,CmsPosition,Long> {

}