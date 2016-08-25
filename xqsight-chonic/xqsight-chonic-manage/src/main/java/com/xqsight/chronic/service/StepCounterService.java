/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.StepCounter;

/**
 * <p>计步器表接口类service</p>
 * <p>Table: STEP_COUNTER - 计步器表</p>
 * @since 2016-05-09 07:51:57
 */
public interface StepCounterService {

	/**
	 * 
	 * @Description: 保存 计步器数据
	 *
	 * @Title: saveStepCounter
	 * @param @param stepCounter    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveStepCounter(StepCounter stepCounter);
	
	/**
	 * 
	 * @Description: 修改  计步器数据
	 *
	 * @Title: updateStepCounter
	 * @param @param stepCounter    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateStepCounter(StepCounter stepCounter);
	
	/**
	 * 
	 * @Description: 删除 计步器数据
	 *
	 * @Title: deleteStepCounter
	 * @param @param stepId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteStepCounter(Long stepId);
	
	/**
	 * 
	 * @Description: 查询 计步器数据
	 *
	 * @Title: queryStepCounter
	 * @param @return    设定文件
	 * @return List<StepCounter>    返回类型
	 * @throws
	 */
	List<StepCounter> queryStepCounter();
	
	/**
	 * 
	 * @Description: 查询 计步器数据
	 *
	 * @Title: queryStepCounterById
	 * @param @param stepId
	 * @param @return    设定文件
	 * @return StepCounter    返回类型
	 * @throws
	 */
	StepCounter queryStepCounterById(Long stepId);
	
	/**
	 * 
	 * @Description: 查询  计步器数据
	 *
	 * @Title: queryStepCounterByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<StepCounter>    返回类型
	 * @throws
	 */
	List<StepCounter> queryStepCounterByUser(String createOprId);
	
	
	/**
	 * 
	 * @Description: 查询 最近durDay天的数据
	 *
	 * @Title: queryStepCounterByUserAndDurDay
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<StepCounter>    返回类型
	 * @throws
	 */
	List<StepCounter> queryStepCounterByUserAndDurDay(String createOprId, int durDay);
}