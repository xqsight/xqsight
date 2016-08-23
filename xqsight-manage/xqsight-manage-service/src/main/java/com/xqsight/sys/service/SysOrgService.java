/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysOrg;

/**
 * <p>组织机构表接口类service</p>
 * <p>Table: sys_org - 组织机构表</p>
 * @since 2016-06-20 07:55:50
 */
public interface SysOrgService {
	
	/** 保存 **/
	void saveSysOrg(SysOrg sysOrg);
	
	/** 修改 **/
	void updateSysOrg(SysOrg sysOrg);
	
	/** 删除 **/
	void deleteSysOrg(Long orgId);

	/** 根据Id查询 **/
	SysOrg querySysOrgById(Long orgId);


	/** 查询 **/
	SysOrg querySysOrgToTree();


	/**
	 * 查询列表
	 *
	 * @param orgName
	 * @param orgCode
	 * @param customCode
	 * @param parentId
     * @return
     */
	List<SysOrg> querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(String orgName, String orgCode, String customCode, Long parentId);
	
}