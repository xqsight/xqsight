/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysMenuService;

/**
 * <p>菜单信息表 controller</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-04-05 05:21:26
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController<SysMenuService,SysMenu,Long> {

}