/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.base.controller.BaseTreeController;
import com.xqsight.system.model.SysOffice;
import com.xqsight.system.service.SysOfficeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>机构表 controller</p>
 * <p>Table: sys_office - 机构表</p>
 * @since 2017-04-05 05:21:31
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/office")
public class SysOfficeController extends BaseTreeController<SysOfficeService,SysOffice,Long> {

}