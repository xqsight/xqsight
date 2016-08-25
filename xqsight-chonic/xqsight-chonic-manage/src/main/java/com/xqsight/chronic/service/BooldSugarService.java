/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.BooldSugar;

/**
 * <p>血糖记录表接口类service</p>
 * <p>Table: BOOLD_SUGAR - 血糖记录表</p>
 * @since 2016-05-09 07:50:39
 */
public interface BooldSugarService {

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: saveBooldSugar
	 * @param @param booldSugar    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveBooldSugar(BooldSugar booldSugar);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: updateBooldSugar
	 * @param @param booldSugar    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateBooldSugar(BooldSugar booldSugar);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: deleteBooldSugar
	 * @param @param booldId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteBooldSugar(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldSugar
	 * @param @return    设定文件
	 * @return List<BooldSugar>    返回类型
	 * @throws
	 */
	List<BooldSugar> queryBooldSugar();
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldSugarById
	 * @param @param booldId
	 * @param @return    设定文件
	 * @return BooldSugar    返回类型
	 * @throws
	 */
	BooldSugar queryBooldSugarById(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryBooldSugarByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<BooldSugar>    返回类型
	 * @throws
	 */
	List<BooldSugar> queryBooldSugarByUser(String createOprId);
	
	/**
	 * 最近durDay天的数据
	 * @Description: TODO
	 *
	 * @Title: queryBooldSugar
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<BooldSugar>    返回类型
	 * @throws
	 */
	List<BooldSugar> queryBooldSugarByUserAndDurDay(String createOprId, int durDay);
}