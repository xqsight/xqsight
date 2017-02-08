/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysDepartment;
import com.xqsight.system.mapper.SysDepartmentMapper;

import java.util.List;


/**
 * <p>部门表实现类service</p>
 * <p>Table: sys_department - 部门表</p>
 * @since 2017-01-07 11:57:06
 * @author wangganggang
 */
@Service
public class SysDepartmentService extends DefaultEntityService<SysDepartment, Long> {

	@Autowired
	private SysDepartmentMapper sysDepartmentMapper;

	@Override
	protected Dao<SysDepartment, Long> getDao() {
		return sysDepartmentMapper;
	}

}