/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.model.StepCounter;
import com.xqsight.chronic.mysqlmapper.StepCounterMapper;
import com.xqsight.chronic.service.StepCounterService;


/**
 * <p>计步器表接口实现类service</p>
 * <p>Table: STEP_COUNTER - 计步器表</p>
 * @since 2016-05-09 07:51:57
 */
 @Service
public class StepCounterServiceImpl implements StepCounterService {

	private static Logger logger = LogManager.getLogger(StepCounterServiceImpl.class); 
	
	@Autowired
	private StepCounterMapper stepCounterMapper;

	/**
	 * 
	 * <p>Title: saveStepCounter</p>
	 * <p>Description: </p>
	 * @param stepCounter
	 * @see com.xqsight.chronic.service.StepCounterService#saveStepCounter(StepCounter)
	 */
	@Override
	public void saveStepCounter(StepCounter stepCounter){
		logger.debug("出入参数:stepCounter={}", JSON.toJSONString(stepCounter));
		stepCounterMapper.deleteStepCounterByCreateTime(stepCounter.getCreateTime());
		stepCounterMapper.saveStepCounter(stepCounter);
	}
	
	/**
	 * 
	 * <p>Title: updateStepCounter</p>
	 * <p>Description: </p>
	 * @param stepCounter
	 * @see com.xqsight.chronic.service.StepCounterService#updateStepCounter(StepCounter)
	 */
	@Override
	public void updateStepCounter(StepCounter stepCounter) {
		logger.debug("出入参数:stepCounter={}", JSON.toJSONString(stepCounter));
		stepCounterMapper.updateStepCounter(stepCounter);
	}
	
	/**
	 * 
	 * <p>Title: deleteStepCounter</p>
	 * <p>Description: </p>
	 * @param stepId
	 * @see com.xqsight.chronic.service.StepCounterService#deleteStepCounter(Long)
	 */
	@Override
	public void deleteStepCounter(Long stepId) {
		logger.debug("出入参数:stepId={}", stepId);
		stepCounterMapper.deleteStepCounter(stepId);
	}
	
	/**
	 * 
	 * <p>Title: queryStepCounter</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.StepCounterService#queryStepCounter()
	 */
	@Override
	public List<StepCounter> queryStepCounter() {
		return stepCounterMapper.queryStepCounter();
	}
	
	/**
	 * 
	 * <p>Title: queryStepCounterById</p>
	 * <p>Description: </p>
	 * @param stepId
	 * @return
	 * @see com.xqsight.chronic.service.StepCounterService#queryStepCounterById(Long)
	 */
	@Override
	public StepCounter queryStepCounterById(Long stepId ){
		logger.debug("出入参数:stepId={}", stepId);
		return stepCounterMapper.queryStepCounterById(stepId);
	}

	/**
	 * <p>Title: queryStepCounter</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.StepCounterService#queryStepCounterByUser(String)
	 */
	@Override
	public List<StepCounter> queryStepCounterByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return stepCounterMapper.queryStepCounterByUser(createOprId);
	}

	/**
	 * <p>Title: queryStepCounter</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.StepCounterService#queryStepCounterByUserAndDurDay(String, int)
	 */
	@Override
	public List<StepCounter> queryStepCounterByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay={}", createOprId,durDay);
		return stepCounterMapper.queryStepCounterByUserAndDurDay(createOprId,durDay);
	}
	
	
}