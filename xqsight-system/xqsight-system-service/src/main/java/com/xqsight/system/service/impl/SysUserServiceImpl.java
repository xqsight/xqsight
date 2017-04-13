/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import com.xqsight.system.mapper.SysUserMapper;
import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>用户信息表实现类 service</p>
 * <p>Table: sys_user - 用户信息表</p>
 * @since 2017-04-05 05:17:20
 * @author wangganggang
 */
@Service
public class SysUserServiceImpl extends AbstractCrudService<SysUserMapper,SysUser, Long> implements SysUserService {

}