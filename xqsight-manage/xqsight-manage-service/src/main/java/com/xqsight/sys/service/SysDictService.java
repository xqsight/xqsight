/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysDictService {

	/**
	 * 新增
	 *
	 * @Title: saveSysDict
	 * @Description: TODO
	 * @param @param sysDict    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysDict(SysDict sysDict);
	
	/**
	 * 新增
	 *
	 * @Title: saveSysDictDetail
	 * @Description: TODO
	 * @param @param sysDictDetail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysDictDetail(SysDictDetail sysDictDetail);
	
	/**
	 * 
	 * updateSysDict 修改
	 * @Title: updateSysDict
	 * @Description: TODO
	 * @param @param sysDict    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysDict(SysDict sysDict);
	
	/**
	 * 
	 * updateSysDictDetail 修改
	 * @Title: updateSysDictDetail
	 * @Description: TODO
	 * @param @param sysDictDetail    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysDictDetail(SysDictDetail sysDictDetail);
	
	/**
	 * 
	 * deleteSysDict 删除 
	 *
	 * @Title: deleteSysDict
	 * @Description: TODO
	 * @param @param DictId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysDict(int dictId);
	
	
	/**
	 * 
	 * @Description: 删除
	 *
	 * @Title: deleteSysDictDetail
	 * @param @param dictDetailId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysDictDetail(int dictDetailId);
	
	/**
	 * 
	 * querySysDictByDictName 查询
	 *
	 * @Title: querySysDictByDictName
	 * @Description: TODO
	 * @param @param DictName
	 * @param @return    设定文件
	 * @return List<SysDict>    返回类型
	 * @throws
	 */
	List<SysDict> querySysDictByDictName(String dictName);
	
	/**
	 * 
	 * querySysDictByDictCode 查询
	 *
	 * @Title: querySysDictByDictCode
	 * @Description: TODO
	 * @param @param dictCode
	 * @param @return    设定文件
	 * @return List<SysDict>    返回类型
	 * @throws
	 */
	List<SysDict> querySysDictByDictCode(String dictCode);
	
	/**
	 * 
	 * querySysDictByDictId 查询
	 *
	 * @Title: querySysDictByDictId
	 * @Description: TODO
	 * @param @param DictId
	 * @param @return    设定文件
	 * @return SysDict    返回类型
	 * @throws
	 */
	SysDict querySysDictByDictId(int dictId);

	
	
	/**
	 * 
	 * querySysDictDetailByDictId 查询
	 *
	 * @Title: querySysDictDetailByDictId
	 * @Description: TODO
	 * @param @param dictid
	 * @param @return    设定文件
	 * @return List<SysDictDetail>    返回类型
	 * @throws
	 */
	List<SysDictDetail> querySysDictDetailByDictId(int dictId);
	
	/**
	 * 
	 * querySysDictDetailByDictDetailId 查询
	 *
	 * @Title: querySysDictDetailByDictDetailId
	 * @Description: TODO
	 * @param @param DictDetailId
	 * @param @return    设定文件
	 * @return SysDictDetail    返回类型
	 * @throws
	 */
	SysDictDetail querySysDictDetailByDictDetailId(int dictDetailId);
}
