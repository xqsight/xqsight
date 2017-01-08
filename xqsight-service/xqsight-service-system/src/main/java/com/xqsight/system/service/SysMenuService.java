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
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>菜单信息表实现类service</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-01-07 11:57:32
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

	/**
	 * 查询当前用
	 * @param userId
	 * @param propertyFilters
	 * @param sorts
	 * @return
	 */
	public List<SysMenu> querySubByUserId(Long userId, List<PropertyFilter> propertyFilters, List<Sort> sorts){
		List<String> menuIds = sysMenuMapper.queryMenuByUser(userId);
		Criterion criterion = new Criterion(propertyFilters,sorts);
		StringBuffer customSql = new StringBuffer();
		menuIds.stream().distinct().forEach(menuId -> {
			customSql.append(" ' ").append(menuId).append(" ' ")
					.append(" in(parent_ids) ").append(" or ");
		});
		criterion.setCustomCriteria(StringUtils.substringBeforeLast(customSql.toString(),"or"));
		return sysMenuMapper.find(criterion);
	}
}