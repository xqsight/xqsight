/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.BooldPressure;

/**
 * <p>血压记录表接口类service</p>
 * <p>Table: BOOLD_PRESSURE - 血压记录表</p>
 * @since 2016-05-09 07:50:31
 */
public interface BooldPressureService {

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: saveBooldPressure
	 * @param @param booldPressure    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveBooldPressure(BooldPressure booldPressure);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: updateBooldPressure
	 * @param @param booldPressure    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateBooldPressure(BooldPressure booldPressure);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: deleteBooldPressure
	 * @param @param booldId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteBooldPressure(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldPressure
	 * @param @return    设定文件
	 * @return List<BooldPressure>    返回类型
	 * @throws
	 */
	List<BooldPressure> queryBooldPressure();
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldPressureById
	 * @param @param booldId
	 * @param @return    设定文件
	 * @return BooldPressure    返回类型
	 * @throws
	 */
	BooldPressure queryBooldPressureById(Long booldId);
	
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldPressureByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<BooldPressure>    返回类型
	 * @throws
	 */
	List<BooldPressure> queryBooldPressureByUser(String createOprId);
	
	/**
	 * 最近durDay天的数据
	 * @Description: TODO
	 *
	 * @Title: queryBooldPressureByUserAndDurDay
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<BooldPressure>    返回类型
	 * @throws
	 */
	List<BooldPressure> queryBooldPressureByUserAndDurDay(String createOprId, int durDay);
}