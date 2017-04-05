/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysUserService;

/**
 * <p>用户信息表 controller</p>
 * <p>Table: sys_user - 用户信息表</p>
 * @since 2017-04-05 05:21:44
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUserService,SysUser,Long> {

}