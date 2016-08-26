package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysRole;
import com.xqsight.sys.mysqlmapper.SysRoleMapper;
import com.xqsight.sys.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:48:31
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	private static Logger logger = LogManager.getLogger(SysRoleServiceImpl.class); 
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * <p>Title: saveSysRole</p>
	 * <p>Description: </p>
	 * @param sysRole
	 */
	@Override
	public void saveSysRole(SysRole sysRole) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysRole));
		sysRoleMapper.saveSysRole(sysRole);
	}

	/**
	 * <p>Title: updateSysRole</p>
	 * <p>Description: </p>
	 * @param sysRole
	 */
	@Override
	public void updateSysRole(SysRole sysRole) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysRole));
		sysRoleMapper.updateSysRole(sysRole);
	}

	/**
	 * <p>Title: deleteSysRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 */
	@Override
	public void deleteSysRole(long roleId) {
		logger.debug("出入参数:{}", roleId);
		sysRoleMapper.deleteSysRole(roleId);
	}

	/**
	 * <p>Title: querySysRoleByRoleName</p>
	 * <p>Description: </p>
	 * @param roleName
	 * @return
	 */
	@Override
	public List<SysRole> querySysRoleByRoleName(String roleName) {
		logger.debug("出入参数:{}", roleName);
		if(StringUtils.isBlank(roleName)){
			roleName = "%%";
		}else {
			roleName = "%" + roleName + "%";
		}
			
		return sysRoleMapper.querySysRoleByRoleName(roleName);
	}

	/**
	 * <p>Title: querySysRoleByRoleId</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @return
	 */
	@Override
	public SysRole querySysRoleByRoleId(long roleId) {
		return sysRoleMapper.querySysRoleByRoleId(roleId);
	}

}
