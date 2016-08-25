/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.Oxygen;

/**
 * <p>血氧记录表接口类service</p>
 * <p>Table: OXYGEN - 血氧记录表</p>
 * @since 2016-05-09 07:51:50
 */
public interface OxygenService {

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: saveOxygen
	 * @param @param oxygen    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOxygen(Oxygen oxygen);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: updateOxygen
	 * @param @param oxygen    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateOxygen(Oxygen oxygen);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: deleteOxygen
	 * @param @param booldId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteOxygen(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryOxygen
	 * @param @return    设定文件
	 * @return List<Oxygen>    返回类型
	 * @throws
	 */
	List<Oxygen> queryOxygen();
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryOxygenById
	 * @param @param booldId
	 * @param @return    设定文件
	 * @return Oxygen    返回类型
	 * @throws
	 */
	Oxygen queryOxygenById(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryOxygenByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<Oxygen>    返回类型
	 * @throws
	 */
	List<Oxygen> queryOxygenByUser(String createOprId);
	
	/**
	 * 查询最近durday天的数据
	 *
	 * @Description: TODO
	 *
	 * @Title: queryOxygenByUserAndDurDay
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<Oxygen>    返回类型
	 * @throws
	 */
	List<Oxygen> queryOxygenByUserAndDurDay(String createOprId, int durDay);
}