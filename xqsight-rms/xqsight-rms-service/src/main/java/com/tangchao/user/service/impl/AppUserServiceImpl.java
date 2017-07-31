/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.user.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.tangchao.user.model.AppUser;
import com.tangchao.user.service.AppUserService;
import com.tangchao.user.mapper.AppUserMapper;

/**
 * <p>实现类 service</p>
 * <p>Table: app_user - </p>
 * @since 2017-07-31 09:40:52
 * @author wangganggang
 */
@Service
public class AppUserServiceImpl extends AbstractCrudService<AppUserMapper,AppUser, Long> implements AppUserService {

}