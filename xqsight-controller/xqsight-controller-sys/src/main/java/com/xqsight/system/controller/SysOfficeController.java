/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.system.model.SysOffice;
import com.xqsight.system.service.SysOfficeService;

/**
 * <p>机构表 controller</p>
 * <p>Table: sys_office - 机构表</p>
 * @since 2017-04-05 05:21:31
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/office")
public class SysOfficeController extends BaseController<SysOfficeService,SysOffice,Long> {

}