/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysRoleService;
import com.xqsight.system.mapper.SysRoleMapper;

/**
 * <p>角色信息表实现类 service</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-04-05 05:17:09
 * @author wangganggang
 */
@Service
public class SysRoleServiceImpl extends AbstractCrudService<SysRoleMapper,SysRole, Long> implements SysRoleService {

}