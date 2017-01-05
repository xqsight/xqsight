/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysDict;
import com.xqsight.system.mapper.SysDictMapper;


/**
 * <p>系统字典表实现类service</p>
 * <p>Table: sys_dict - 系统字典表</p>
 * @since 2017-01-05 06:10:27
 * @author wangganggang
 */
@Service
public class SysDictService extends DefaultEntityService<SysDict, Long> {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	protected Dao<SysDict, Long> getDao() {
		return sysDictMapper;
	}
}