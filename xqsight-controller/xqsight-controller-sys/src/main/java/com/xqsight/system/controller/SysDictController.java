/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.system.model.SysDict;
import com.xqsight.system.service.SysDictService;

/**
 * <p>系统字典表 controller</p>
 * <p>Table: sys_dict - 系统字典表</p>
 * @since 2017-04-05 05:21:13
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDictService,SysDict,Long> {

}