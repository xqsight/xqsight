/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysFile;
import com.xqsight.system.mapper.SysFileMapper;


/**
 * <p>文件表实现类service</p>
 * <p>Table: sys_file - 文件表</p>
 * @since 2017-01-07 11:57:19
 * @author wangganggang
 */
@Service
public class SysFileService extends DefaultEntityService<SysFile, Long> {

	@Autowired
	private SysFileMapper sysFileMapper;

	@Override
	protected Dao<SysFile, Long> getDao() {
		return sysFileMapper;
	}
}