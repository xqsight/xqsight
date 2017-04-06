/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.base.controller.BaseTreeController;
import com.xqsight.system.model.SysArea;
import com.xqsight.system.service.SysAreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>区域表 controller</p>
 * <p>Table: sys_area - 区域表</p>
 *
 * @author wangganggang
 * @since 2017-04-05 05:21:08
 */
@RestController
@RequestMapping("/sys/area")
public class SysAreaController extends BaseTreeController<SysAreaService, SysArea, Long> {

}