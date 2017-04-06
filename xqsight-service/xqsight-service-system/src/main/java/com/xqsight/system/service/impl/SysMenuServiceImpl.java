/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>菜单信息表实现类 service</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-04-05 05:16:59
 * @author wangganggang
 */
@Service
public class SysMenuServiceImpl extends AbstractCrudService<SysMenuMapper,SysMenu, Long> implements SysMenuService {

}