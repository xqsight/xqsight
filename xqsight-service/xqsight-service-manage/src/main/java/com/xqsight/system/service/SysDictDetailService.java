/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysDictDetail;
import com.xqsight.system.mapper.SysDictDetailMapper;


/**
 * <p>系统字典明细表实现类service</p>
 * <p>Table: sys_dict_detail - 系统字典明细表</p>
 * @since 2017-01-05 06:10:32
 * @author wangganggang
 */
@Service
public class SysDictDetailService extends DefaultEntityService<SysDictDetail, Long> {

	@Autowired
	private SysDictDetailMapper sysDictDetailMapper;

	@Override
	protected Dao<SysDictDetail, Long> getDao() {
		return sysDictDetailMapper;
	}
}