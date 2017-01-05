package com.xqsight.authc.service.impl;

import com.xqsight.authc.mapper.SysUserAuthcMapper;
import com.xqsight.common.model.UserBaseModel;
import com.xqsight.common.model.shiro.Resource;
import com.xqsight.common.model.shiro.Role;
import com.xqsight.sso.authc.service.UserAuthcService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午4:35:18
 */
@Service
public class SysUserAuthcServiceImpl implements UserAuthcService {

	@Autowired(required=false)
	private SysUserAuthcMapper sysUserAuthcMapper;

	/**
	 * <p>Title: correlationRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @param roleIds
	 * @see UserAuthcService#correlationRoles(long, Long[])
	 */
	@Override
	public void correlationRoles(long id, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <p>Title: uncorrelationRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @param roleIds
	 * @see UserAuthcService#uncorrelationRoles(long, Long[])
	 */
	@Override
	public void uncorrelationRoles(long id, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <p>Title: findByLoginId</p>
	 * <p>Description: </p>
	 * @param loginId
	 * @return
	 * @see UserAuthcService#findByLoginId(String)
	 */
	@Override
	public UserBaseModel findByLoginId(String loginId) {
		 return sysUserAuthcMapper.queryUserByLoginId(loginId);
	}

	/**
	 * <p>Title: findRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see UserAuthcService#findRoles(long)
	 */
	@Override
	public Set<String> findRoles(long id) {
		List<Role> roleList = sysUserAuthcMapper.queryUserRoleById(id);
		Set<String> roleSet = new HashSet<String>();
		for (Role role : roleList) {
			roleSet.add(role.getRole());
		}
		return roleSet;
	}

	/**
	 * <p>Title: findPermissions</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see UserAuthcService#findPermissions(long)
	 */
	@Override
	public Set<String> findPermissions(long id) {
		List<Role> roleList = sysUserAuthcMapper.queryUserRoleById(id);
		Set<String> permissionSet = new HashSet<String>();
		for (Role role : roleList) {
			List<Resource> resourceList = sysUserAuthcMapper.queryResourceByRoleId(role.getId());
			for (Resource resource : resourceList) {
				if(resource != null && StringUtils.isNotBlank(resource.getPermission()))
					permissionSet.add(resource.getPermission());
			}
		}
		return permissionSet;
	}

	/**
	 * <p>Title: saveUser</p>
	 * <p>Description: </p>
	 * @param userBaseModel
	 * @see UserAuthcService#saveUser(UserBaseModel)
	 */
	@Override
	public void saveUser(UserBaseModel userBaseModel) {
		sysUserAuthcMapper.saveUserLogin(userBaseModel);
	}
}
