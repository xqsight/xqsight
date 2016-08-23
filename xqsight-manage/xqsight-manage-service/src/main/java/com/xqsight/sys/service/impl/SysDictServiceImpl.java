/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import com.xqsight.sys.mysqlmapper.SysDictMapper;
import com.xqsight.sys.service.SysDictService;

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
	 * @see com.xqsight.sys.service.SysDictService#saveSysDict(SysDict)
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
	 * @see com.xqsight.sys.service.SysDictService#updateSysDict(SysDict)
	 */
	@Override
	public void updateSysDict(SysDict sysDict) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDict));
		sysDictMapper.updateSysDict(sysDict);
	}

	/**
	 * <p>Title: deleteSysDict</p>
	 * <p>Description: </p>
	 * @param DictId
	 * @see com.xqsight.sys.service.SysDictService#deleteSysDict(int)
	 */
	@Override
	public void deleteSysDict(int dictId) {
		logger.debug("出入参数:{}", dictId);
		sysDictMapper.deleteSysDict(dictId);
		sysDictMapper.deleteSysDictDetailByDictId(dictId);
	}

	/**
	 * <p>Title: querySysDictByDictName</p>
	 * <p>Description: </p>
	 * @param DictName
	 * @return
	 * @see com.xqsight.sys.service.SysDictService#querySysDictByDictName(String)
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
	 * @see com.xqsight.sys.service.SysDictService#querySysDictByDictCode(String)
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
	 * @see com.xqsight.sys.service.SysDictService#querySysDictByDictId(int)
	 */
	@Override
	public SysDict querySysDictByDictId(int DictId) {
		return sysDictMapper.querySysDictByDictId(DictId);
	}

	/**
	 * <p>Title: saveSysDictDetail</p>
	 * <p>Description: </p>
	 * @param sysDictDetail
	 * @see com.xqsight.sys.service.SysDictService#saveSysDictDetail(SysDictDetail)
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
	 * @see com.xqsight.sys.service.SysDictService#updateSysDictDetail(SysDictDetail)
	 */
	@Override
	public void updateSysDictDetail(SysDictDetail sysDictDetail) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysDictDetail));
		sysDictMapper.updateSysDictDetail(sysDictDetail);
	}

	/**
	 * <p>Title: deleteSysDictDetail</p>
	 * <p>Description: </p>
	 * @param DictDetailId
	 * @see com.xqsight.sys.service.SysDictService#deleteSysDictDetail(int)
	 */
	@Override
	public void deleteSysDictDetail(int dictDetailId) {
		logger.debug("出入参数:{}", dictDetailId);
		sysDictMapper.deleteSysDictDetail(dictDetailId);
	}

	/**
	 * <p>Title: querySysDictDetailByDictId</p>
	 * <p>Description: </p>
	 * @param dictid
	 * @return
	 * @see com.xqsight.sys.service.SysDictService#querySysDictDetailByDictId(int)
	 */
	@Override
	public List<SysDictDetail> querySysDictDetailByDictId(int dictId) {
		logger.debug("出入参数:{}", dictId);
		return sysDictMapper.querySysDictDetailByDictId(dictId);
	}

	/**
	 * <p>Title: querySysDictDetailByDictDetailId</p>
	 * <p>Description: </p>
	 * @param DictDetailId
	 * @return
	 * @see com.xqsight.sys.service.SysDictService#querySysDictDetailByDictDetailId(int)
	 */
	@Override
	public SysDictDetail querySysDictDetailByDictDetailId(int dictDetailId) {
		return sysDictMapper.querySysDictDetailByDictDetailId(dictDetailId);
	}
}
