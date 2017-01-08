/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.orm.Criterion;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.Sort;
import com.xqsight.common.service.DefaultEntityService;

import com.xqsight.system.model.SysDepartment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.mapper.SysRoleMapper;

import java.util.List;


/**
 * <p>角色信息表实现类service</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-01-07 11:58:03
 * @author wangganggang
 */
@Service
public class SysRoleService extends DefaultEntityService<SysRole, Long> {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	protected Dao<SysRole, Long> getDao() {
		return sysRoleMapper;
	}

	/**
	 * 查询当前用
	 * @param userId
	 * @param propertyFilters
	 * @param sorts
	 * @return
	 */
	public List<SysRole> querySubByUserId(Long userId, List<PropertyFilter> propertyFilters, List<Sort> sorts){
		List<String> roleIds = sysRoleMapper.queryRoleByUser(userId);
		Criterion criterion = new Criterion(propertyFilters,sorts);
		StringBuffer customSql = new StringBuffer();
		roleIds.stream().distinct().forEach(roleId -> {
			customSql.append(" ' ").append(roleId).append(" ' ")
					.append(" in(parent_ids) ").append(" or ");
		});
		criterion.setCustomCriteria(StringUtils.substringBeforeLast(customSql.toString(),"or"));
		return sysRoleMapper.find(criterion);
	}
}