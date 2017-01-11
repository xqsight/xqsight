/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysQuickKey;
import com.xqsight.system.mapper.SysQuickKeyMapper;


/**
 * <p>快捷键表实现类service</p>
 * <p>Table: sys_quick_key - 快捷键表</p>
 * @since 2017-01-07 11:57:57
 * @author wangganggang
 */
@Service
public class SysQuickKeyService extends DefaultEntityService<SysQuickKey, Long> {

	@Autowired
	private SysQuickKeyMapper sysQuickKeyMapper;

	@Override
	protected Dao<SysQuickKey, Long> getDao() {
		return sysQuickKeyMapper;
	}
}