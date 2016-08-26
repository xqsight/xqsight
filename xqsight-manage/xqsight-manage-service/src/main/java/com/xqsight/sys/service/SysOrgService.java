 package com.xqsight.sys.service;

import com.xqsight.sys.model.SysOrg;

import java.util.List;

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
	void deleteSysOrg(long orgId);

	/** 根据Id查询 **/
	SysOrg querySysOrgById(long orgId);

	/** 查询 **/
	SysOrg querySysOrgToTree();

	/** 查询列表 **/
	List<SysOrg> querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(String orgName, String orgCode, String customCode, Long parentId);
	
}