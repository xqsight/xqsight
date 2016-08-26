package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysQuickKey;
import com.xqsight.sys.mysqlmapper.SysQuickKeyMapper;
import com.xqsight.sys.service.SysQuickKeyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	 */
	@Override
	public List<SysQuickKey> querySysQuickKeyById(long id) {
		return sysQuickKeyMapper.querySysQuickKeyById(id);
	}

}
