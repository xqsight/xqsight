/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.system.model.SysStation;
import com.xqsight.system.service.SysStationService;

/**
 * <p>岗位信息表 controller</p>
 * <p>Table: sys_station - 岗位信息表</p>
 * @since 2017-04-05 05:21:40
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/station")
public class SysStationController extends BaseController<SysStationService,SysStation,Long> {

}