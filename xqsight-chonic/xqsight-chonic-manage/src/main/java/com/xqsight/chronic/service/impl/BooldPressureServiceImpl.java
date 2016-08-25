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
import com.xqsight.chronic.model.BooldPressure;
import com.xqsight.chronic.mysqlmapper.BooldPressureMapper;
import com.xqsight.chronic.service.BooldPressureService;


/**
 * <p>血压记录表接口实现类service</p>
 * <p>Table: BOOLD_PRESSURE - 血压记录表</p>
 * @since 2016-05-09 07:50:31
 */
 @Service
public class BooldPressureServiceImpl implements BooldPressureService {

	private static Logger logger = LogManager.getLogger(BooldPressureServiceImpl.class); 
	
	@Autowired
	private BooldPressureMapper booldPressureMapper;

	/**
	 * 
	 * <p>Title: saveBooldPressure</p>
	 * <p>Description: </p>
	 * @param booldPressure
	 * @see com.xqsight.chronic.service.BooldPressureService#saveBooldPressure(BooldPressure)
	 */
	@Override
	public void saveBooldPressure(BooldPressure booldPressure){
		logger.debug("出入参数:booldPressure={}", JSON.toJSONString(booldPressure));
		booldPressureMapper.saveBooldPressure(booldPressure);
	}
	
	/**
	 * 
	 * <p>Title: updateBooldPressure</p>
	 * <p>Description: </p>
	 * @param booldPressure
	 * @see com.xqsight.chronic.service.BooldPressureService#updateBooldPressure(BooldPressure)
	 */
	@Override
	public void updateBooldPressure(BooldPressure booldPressure) {
		logger.debug("出入参数:booldPressure={}", JSON.toJSONString(booldPressure));
		booldPressureMapper.updateBooldPressure(booldPressure);
	}
	
	/**
	 * 
	 * <p>Title: deleteBooldPressure</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @see com.xqsight.chronic.service.BooldPressureService#deleteBooldPressure(Long)
	 */
	@Override
	public void deleteBooldPressure(Long booldId) {
		logger.debug("出入参数:booldId={}", booldId);
		booldPressureMapper.deleteBooldPressure(booldId);
	}
	
	/**
	 * 
	 * <p>Title: queryBooldPressure</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.BooldPressureService#queryBooldPressure()
	 */
	@Override
	public List<BooldPressure> queryBooldPressure() {
		return booldPressureMapper.queryBooldPressure();
	}
	
	/**
	 * 
	 * <p>Title: queryBooldPressureById</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @return
	 * @see com.xqsight.chronic.service.BooldPressureService#queryBooldPressureById(Long)
	 */
	@Override
	public BooldPressure queryBooldPressureById(Long booldId ){
		logger.debug("出入参数:booldId={}", booldId);
		return booldPressureMapper.queryBooldPressureById(booldId);
	}

	/**
	 * <p>Title: queryBooldPressure</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.BooldPressureService#queryBooldPressureByUser(String)
	 */
	@Override
	public List<BooldPressure> queryBooldPressureByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return booldPressureMapper.queryBooldPressureByUser(createOprId);
	}

	/**
	 * <p>Title: queryBooldPressure</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.BooldPressureService#queryBooldPressureByUserAndDurDay(String, int)
	 */
	@Override
	public List<BooldPressure> queryBooldPressureByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay={}", createOprId,durDay);
		return booldPressureMapper.queryBooldPressureByUserAndDurDay(createOprId, durDay);
	}
}