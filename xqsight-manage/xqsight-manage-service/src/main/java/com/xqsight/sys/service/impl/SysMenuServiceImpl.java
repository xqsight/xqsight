/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.mysqlmapper.SysMenuMapper;
import com.xqsight.sys.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午2:08:06
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

private static Logger logger = LogManager.getLogger(SysMenuServiceImpl.class); 
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	

	@Override
	public void saveSysMenu(SysMenu sysMenu) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysMenu));
		sysMenuMapper.saveSysMenu(sysMenu);
	}

	@Override
	public void updateSysMenu(SysMenu sysMenu) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysMenu));
		sysMenuMapper.updateSysMenu(sysMenu);
	}

	@Override
	public void deleteSysMenu(Long menuId) {
		logger.debug("出入参数:{}", menuId);
		sysMenuMapper.deleteSysMenu(menuId);
		sysMenuMapper.deleteSysMenuRoleByMenuId(menuId);
	}

	@Override
	public SysMenu querySysMenuByMenuId(Long menuId) {
		logger.debug("出入参数:menuId={}", menuId);
		return sysMenuMapper.querySysMenuByMenuId(menuId);
	}

	@Override
	public SysMenu querySysMenuForTree() {
		List<SysMenu> sysMenus = sysMenuMapper.querySysMenuForTree();
		return new TreeSupport<SysMenu>().generateFullTree(sysMenus);
	}

	@Override
	public List<SysMenu> querySysMenuByMenuNameAndParentId(String menuName,Long parentId) {
		logger.debug("出入参数:menuName={},parentId={}", menuName,parentId);
		return sysMenuMapper.querySysMenuByMenuNameAndParentId(menuName,parentId);
	}

	@Override
	public List<SysMenu> querySyeMenuByRoleId(Long roleId) {
		logger.debug("出入参数:roleId={}", roleId);
		return sysMenuMapper.querySyeMenuByRoleId(roleId);
	}

	@Override
	public List<SysMenu> querySysMenuByUser(Long userId) {
		logger.debug("出入参数:userId={}", userId);
		List<SysMenu> sysMenus = sysMenuMapper.querySysMenuByUser(userId);
		return new TreeSupport<SysMenu>().generateTree(sysMenus);
	}

	@Override
	public SysMenu queryMenuAllConvertTree() {
		List<SysMenu> sysMenus = sysMenuMapper.querySysMenuAllForTree();
		return new TreeSupport<SysMenu>().generateFullTree(sysMenus);
	}

	@Override
	public List<SysMenu> querySysMenu() {
		return sysMenuMapper.querySysMenu();
	}
}
