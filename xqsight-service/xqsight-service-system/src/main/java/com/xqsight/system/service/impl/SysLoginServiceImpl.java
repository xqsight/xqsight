/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysLogin;
import com.xqsight.system.service.SysLoginService;
import com.xqsight.system.mapper.SysLoginMapper;

/**
 * <p>用户登陆表实现类 service</p>
 * <p>Table: sys_login - 用户登陆表</p>
 * @since 2017-04-06 10:55:37
 * @author wangganggang
 */
@Service
public class SysLoginServiceImpl extends AbstractCrudService<SysLoginMapper,SysLogin, Long> implements SysLoginService {

}