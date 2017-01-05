package com.xqsight.sso.authc.service;



import com.xqsight.common.model.shiro.Resource;

import java.util.List;
import java.util.Set;

public interface ResourceAuthcService {

	/**
	 * 
	 * findOne查找资源
	 *
	 * @Title: findOne
	 * @Description: TODO
	 * @param @param resourceId
	 * @param @return    设定文件
	 * @return Resource    返回类型
	 * @throws
	 */
    Resource findOne(Long resourceId);
    
    /**
     * 
     * findAll查找所有资源
     *
     * @Title: findAll
     * @Description: TODO
     * @param @return    设定文件
     * @return List<Resource>    返回类型
     * @throws
     */
    List<Resource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);
}
