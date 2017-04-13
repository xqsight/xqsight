/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.base.controller.BaseController;
import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>角色信息表 controller</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-04-05 05:21:35
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController<SysRoleService,SysRole,Long> {

}