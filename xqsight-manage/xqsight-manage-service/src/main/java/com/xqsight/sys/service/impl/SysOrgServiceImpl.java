 package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.sys.model.SysOrg;
import com.xqsight.sys.mysqlmapper.SysOrgMapper;
import com.xqsight.sys.service.SysOrgService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>组织机构表接口实现类service</p>
 * <p>Table: sys_org - 组织机构表</p>
 * @since 2016-06-20 07:55:50
 */
 @Service
public class SysOrgServiceImpl implements SysOrgService {

	private static Logger logger = LogManager.getLogger(SysOrgServiceImpl.class); 
	
	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Override
	public void saveSysOrg(SysOrg sysOrg){
		logger.debug("出入参数:sysOrg＝{}", JSON.toJSONString(sysOrg));
		sysOrgMapper.saveSysOrg(sysOrg);
	}
	
	@Override
	public void updateSysOrg(SysOrg sysOrg) {
		logger.debug("出入参数:sysOrg＝{}", JSON.toJSONString(sysOrg));
		sysOrgMapper.updateSysOrg(sysOrg);
	}
	
	@Override
	public void deleteSysOrg(long orgId) {
		logger.debug("出入参数:orgId＝{}", orgId);
		sysOrgMapper.deleteSysOrg(orgId);
	}
	
	@Override
	public SysOrg querySysOrgToTree() {
		List<SysOrg> sysOrgs = sysOrgMapper.querySysOrg();
		return new TreeSupport<SysOrg>().generateFullTree(sysOrgs);
	}
	
	@Override
	public SysOrg querySysOrgById(long orgId ){
		return sysOrgMapper.querySysOrgById(orgId);
	}

	@Override
	public List<SysOrg> querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(String orgName, String orgCode, String customCode, Long parentId) {
		logger.debug("出入参数:orgName={},orgCode={},customCode={},parentId={}", orgName,orgCode,customCode,parentId);
		return sysOrgMapper.querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(orgName,orgCode,customCode,parentId);
	}
}