/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.Fat;

/**
 * <p>脂肪记录表接口类service</p>
 * <p>Table: FAT - 脂肪记录表</p>
 * @since 2016-05-09 07:50:53
 */
public interface FatService {

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: saveFat
	 * @param @param fat    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveFat(Fat fat);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: updateFat
	 * @param @param fat    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateFat(Fat fat);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: deleteFat
	 * @param @param booldId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteFat(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryFat
	 * @param @return    设定文件
	 * @return List<Fat>    返回类型
	 * @throws
	 */
	List<Fat> queryFat();
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryFatById
	 * @param @param booldId
	 * @param @return    设定文件
	 * @return Fat    返回类型
	 * @throws
	 */
	Fat queryFatById(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryFatByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<Fat>    返回类型
	 * @throws
	 */
	List<Fat> queryFatByUser(String createOprId);
	
	/**
	 * 查询最近durday天的数据
	 * @Description: TODO
	 *
	 * @Title: queryFat
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<Fat>    返回类型
	 * @throws
	 */
	List<Fat> queryFatByUserAndDurDay(String createOprId, int durDay);
	
}