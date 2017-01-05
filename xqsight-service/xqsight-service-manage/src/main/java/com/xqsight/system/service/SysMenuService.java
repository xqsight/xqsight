/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysMenu;
import com.xqsight.system.mapper.SysMenuMapper;


/**
 * <p>菜单信息表实现类service</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-01-05 06:10:54
 * @author wangganggang
 */
@Service
public class SysMenuService extends DefaultEntityService<SysMenu, Long> {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	protected Dao<SysMenu, Long> getDao() {
		return sysMenuMapper;
	}
}