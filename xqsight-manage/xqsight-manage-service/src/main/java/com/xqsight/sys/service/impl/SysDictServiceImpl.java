/**
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.mysqlmapper.SysDictMapper;
import com.xqsight.sys.service.SysDictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:48:31
 */
@Service
public class SysDictServiceImpl implements SysDictService {
	
	private static Logger logger = LogManager.getLogger(SysDictServiceImpl.class); 
	
	@Autowired
	private SysDictMapper sysDictMapper;

	/**
	 * <p>Title: saveSysDict</p>
	 * <p>Description: </p>
	 * @param sysDict
	 */
	@Override
	public void saveSysDict(SysDict sysDict) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDict));
		sysDictMapper.saveSysDict(sysDict);
	}

	/**
	 * <p>Title: updateSysDict</p>
	 * <p>Description: </p>
	 * @param sysDict
	 */
	@Override
	public void updateSysDict(SysDict sysDict) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDict));
		sysDictMapper.updateSysDict(sysDict);
	}

	/**
	 * <p>Title: deleteSysDict</p>
	 * <p>Description: </p>
	 */
	@Override
	public void deleteSysDict(long dictId) {
		logger.debug("出入参数:{}", dictId);
		sysDictMapper.deleteSysDict(dictId);
		sysDictMapper.deleteSysDictDetailByDictId(dictId);
	}

	/**
	 * <p>Title: querySysDictByDictName</p>
	 * <p>Description: </p>
	 * @return
	 */
	@Override
	public List<SysDict> querySysDictByDictName(String dictName) {
		logger.debug("出入参数:{}", dictName);
		if(StringUtils.isBlank(dictName)){
			dictName = "%%";
		}else {
			dictName = "%" + dictName + "%";
		}
			
		return sysDictMapper.querySysDictByDictName(dictName);
	}
	

	/**
	 * <p>Title: querySysDictByDictCode</p>
	 * <p>Description: </p>
	 * @param dictCode
	 * @return
	 */
	@Override
	public List<SysDict> querySysDictByDictCode(String dictCode) {
		return sysDictMapper.querySysDictByDictCode(dictCode);
	}

	/**
	 * <p>Title: querySysDictByDictId</p>
	 * <p>Description: </p>
	 * @param DictId
	 * @return
	 */
	@Override
	public SysDict querySysDictByDictId(long DictId) {
		return sysDictMapper.querySysDictByDictId(DictId);
	}

	/**
	 * <p>Title: saveSysDictDetail</p>
	 * <p>Description: </p>
	 * @param sysDictDetail
	 */
	@Override
	public void saveSysDictDetail(SysDictDetail sysDictDetail) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDictDetail));
		sysDictMapper.saveSysDictDetail(sysDictDetail);
	}

	/**
	 * <p>Title: updateSysDictDetail</p>
	 * <p>Description: </p>
	 * @param sysDictDetail
	 */
	@Override
	public void updateSysDictDetail(SysDictDetail sysDictDetail) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDictDetail));
		sysDictMapper.updateSysDictDetail(sysDictDetail);
	}

	/**
	 * <p>Title: deleteSysDictDetail</p>
	 * <p>Description: </p>
	 */
	@Override
	public void deleteSysDictDetail(long dictDetailId) {
		logger.debug("出入参数:{}", dictDetailId);
		sysDictMapper.deleteSysDictDetail(dictDetailId);
	}

	/**
	 * <p>Title: querySysDictDetailByDictId</p>
	 * <p>Description: </p>
	 * @return
	 */
	@Override
	public List<SysDictDetail> querySysDictDetailByDictId(long dictId) {
		logger.debug("出入参数:{}", dictId);
		return sysDictMapper.querySysDictDetailByDictId(dictId);
	}

	/**
	 * <p>Title: querySysDictDetailByDictDetailId</p>
	 * <p>Description: </p>
	 * @return
	 */
	@Override
	public SysDictDetail querySysDictDetailByDictDetailId(long dictDetailId) {
		return sysDictMapper.querySysDictDetailByDictDetailId(dictDetailId);
	}
}
