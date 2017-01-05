/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysQuickKey;
import com.xqsight.system.mapper.SysQuickKeyMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;


/**
 * <p>快捷键表实现类service</p>
 * <p>Table: sys_quick_key - 快捷键表</p>
 * @since 2017-01-05 06:11:05
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

	public void save(Long id,List<SysQuickKey> sysQuickKeys){
		sysQuickKeyMapper.deleteByPrimaryKey(id);
		for(SysQuickKey sysQuickKey : sysQuickKeys){
			sysQuickKeyMapper.insertSelective(sysQuickKey);
		}
	}
}