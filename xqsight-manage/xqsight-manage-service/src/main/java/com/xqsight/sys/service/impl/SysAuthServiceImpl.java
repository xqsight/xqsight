package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.mysqlbatchmapper.BSysAuthMapper;
import com.xqsight.sys.mysqlmapper.SysAuthMapper;
import com.xqsight.sys.service.SysAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:24:31
 */
@Service
public class SysAuthServiceImpl implements SysAuthService {

	private static Logger logger = LogManager.getLogger(SysAuthServiceImpl.class); 
	
	@Autowired
	private BSysAuthMapper bSysAuthMapper;
	
	@Autowired
	private SysAuthMapper sysAuthMapper;

	/**
	 * <p>Title: saveUserRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @param ids
	 */
	@Override
	public void saveUserRole(long roleId, Long... ids) {
		logger.debug("传入roleId:{},userId：{}",roleId,JSON.toJSONString(ids));
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
	@Override
	public void saveMenuRole(long roleId, Long... menuIds) {
		logger.debug("传入roleId:{},userId：{}",roleId,JSON.toJSONString(menuIds));
		bSysAuthMapper.deleMenuRole(roleId);
		if(menuIds != null &&  !"".equals(menuIds)){
			for(long menuId : menuIds){
				bSysAuthMapper.saveMenuRole(menuId, roleId);
			}
		}
	}

	/**
	 * <p>Title: querAuthUser</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @return
	 */
	@Override
	public List<SysLogin> querAuthUser(long roleId) {
		return sysAuthMapper.queryAuthUser(roleId);
	}

}
