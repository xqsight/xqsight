/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.mysqlmapper.SysUserMapper;
import com.xqsight.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午2:11:03
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	private static Logger logger = LogManager.getLogger(SysUserServiceImpl.class); 
	
	@Autowired
	private SysUserMapper sysLoginMapper;
	
	/**
	 * <p>Title: saveSysLogin</p>
	 * <p>Description: </p>
	 * @param sysLogin
	 * @see com.xqsight.sys.service.SysUserService#saveSysLogin(SysLogin)
	 */
	@Override
	public void saveSysLogin(SysLogin sysLogin) {
		logger.debug("出入参数:sysLogin={}", JSON.toJSONString(sysLogin));
		sysLoginMapper.saveSysLogin(sysLogin);
	}

	
	/**
	 * <p>Title: updateSysLogin</p>
	 * <p>Description: </p>
	 * @param
	 */
	@Override
	public void updateSysLoginPwd(String password, Long id) {
		logger.debug("出入参数:password={},id={}", password,id);
		sysLoginMapper.updateSysLoginPwd(password,id);
	}
	
	/**
	 * <p>Title: updUserName</p>
	 * <p>Description: </p>
	 * @param sysLogin
	 */
	@Override
	public void updSysLogin(SysLogin sysLogin) {
		logger.debug("出入参数:sysLogin={}", JSON.toJSONString(sysLogin));
		sysLoginMapper.updSysLogin(sysLogin);
	}
	

	/**
	 * <p>Title: deleteSysLogin</p>
	 * <p>Description: </p>
	 * @param id
	 * @see com.xqsight.sys.service.SysUserService#deleteSysLogin(Long)
	 */
	@Override
	public void deleteSysLogin(Long id) {
		logger.debug("出入参数:id={}", id);
		sysLoginMapper.deleteSysLogin(id);
		sysLoginMapper.deleteUserRole(id);
	}

	/**
	 * <p>Title: querySysLogin</p>
	 * <p>Description: </p>
	 * @return
	 */
	@Override
	public List<SysLogin> querySysLoginByUserNameAndLoginIdAndOrgId(String userName,String loginId,Long orgId) {
		logger.debug("出入参数:loginId={},userName={},orgId={}", loginId,userName,orgId);
		return sysLoginMapper.querySysLoginByUserNameAndLoginIdAndOrgId(userName,loginId,orgId);
	}


	/**
	 * <p>Title: querySysLoginById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	@Override
	public SysLogin querySysLoginById(Long id) {
		logger.debug("出入参数:id={}", id);
		return sysLoginMapper.querySysLoginById(id);
	}

	@Override
	public void updUserImg(String imgUrl, Long id) {
		logger.debug("出入参数:imgUrl={},id={}", imgUrl,id);
		sysLoginMapper.updateUserImg(imgUrl,id);
	}

	@Override
	public SysLogin querySingleUserByLoginId(String loginId) {
		logger.debug("出入参数:loginId={}", loginId);
		return sysLoginMapper.querySingleUserByLoginId(loginId);
	}
}
