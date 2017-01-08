package com.xqsight.system.service;

import com.xqsight.system.mapper.SysLoginMapper;
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.mapper.batch.BSysAuthMapper;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:24:31
 */
@Service
public class SysAuthService{

	@Autowired
	private BSysAuthMapper bSysAuthMapper;

	@Autowired
	private SysLoginMapper sysLoginMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;
	

	/**
	 * <p>Title: saveUserRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @param ids
	 */
	public void saveUserRole(long roleId, Long... ids) {
		bSysAuthMapper.deleUserRole(roleId);
		if(ids != null &&  !"".equals(ids)){
			for(long id : ids){
				bSysAuthMapper.saveUserRole(id, roleId);
			}
		}
	}

	/**
	 * <p>Title: saveMenuRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @param menuIds
	 */
	public void saveMenuRole(long roleId, Long... menuIds) {
		bSysAuthMapper.deleMenuRole(roleId);
		if(menuIds != null &&  !"".equals(menuIds)){
			for(long menuId : menuIds){
				bSysAuthMapper.saveMenuRole(menuId, roleId);
			}
		}
	}

	public List<SysLogin> querAuthUser(long roleId){
		List<String> userIds = bSysAuthMapper.queryUserIdByRole(roleId);

		return null;
	}

	public List<SysMenu> querAuthMenu(long roleId){
		List<String> menuIds = bSysAuthMapper.queryMenuIdByRole(roleId);
		return null;
	}

}
