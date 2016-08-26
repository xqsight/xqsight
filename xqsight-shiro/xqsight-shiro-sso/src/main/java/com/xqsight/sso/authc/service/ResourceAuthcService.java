/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc.service;

import com.xqsight.sso.shiro.model.Resource;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author linhaoran
 * @version ResourceAuthcService.java, v 0.1 2015年8月5日 上午10:49:29 linhaoran
 */
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
