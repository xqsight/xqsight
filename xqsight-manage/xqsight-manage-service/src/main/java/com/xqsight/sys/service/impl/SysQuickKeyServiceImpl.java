/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysQuickKey;
import com.xqsight.sys.mysqlmapper.SysQuickKeyMapper;
import com.xqsight.sys.service.SysQuickKeyService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年3月23日 下午5:34:20
 *
 */
@Service
public class SysQuickKeyServiceImpl implements SysQuickKeyService {

	private static Logger logger = LogManager.getLogger(SysQuickKeyServiceImpl.class); 
	
	@Autowired
	private SysQuickKeyMapper sysQuickKeyMapper;
	
	/**
	 * <p>Title: saveSysQuickKey</p>
	 * <p>Description: </p>
	 * @param sysQuickKey
	 * @see com.saicfc.pmpf.sys.service.SysQuickKeyService#saveSysQuickKey(com.saicfc.pmpf.sys.service.SysQuickKeyService)
	 */
	@Override
	public void saveSysQuickKey(long id,List<SysQuickKey> sysQuickKeyList) {
		logger.debug("出入参数:id={},params={}", id,JSON.toJSONString(sysQuickKeyList));
		sysQuickKeyMapper.deleteSysQuickKey(id);
		for (SysQuickKey sysQuickKey : sysQuickKeyList) {
			sysQuickKeyMapper.saveSysQuickKey(sysQuickKey);
		}
	}

	/**
	 * <p>Title: querySysQuickKeyById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see com.saicfc.pmpf.sys.service.SysQuickKeyService#querySysQuickKeyById(long)
	 */
	@Override
	public List<SysQuickKey> querySysQuickKeyById(long id) {
		return sysQuickKeyMapper.querySysQuickKeyById(id);
	}

}
