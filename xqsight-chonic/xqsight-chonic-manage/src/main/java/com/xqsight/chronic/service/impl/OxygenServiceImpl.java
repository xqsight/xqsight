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
import com.xqsight.chronic.model.Oxygen;
import com.xqsight.chronic.mysqlmapper.OxygenMapper;
import com.xqsight.chronic.service.OxygenService;


/**
 * <p>血氧记录表接口实现类service</p>
 * <p>Table: OXYGEN - 血氧记录表</p>
 * @since 2016-05-09 07:51:50
 */
 @Service
public class OxygenServiceImpl implements OxygenService {

	private static Logger logger = LogManager.getLogger(OxygenServiceImpl.class); 
	
	@Autowired
	private OxygenMapper oxygenMapper;

	/**
	 * 
	 * <p>Title: saveOxygen</p>
	 * <p>Description: </p>
	 * @param oxygen
	 * @see com.xqsight.chronic.service.OxygenService#saveOxygen(Oxygen)
	 */
	@Override
	public void saveOxygen(Oxygen oxygen){
		logger.debug("出入参数:oxygen={}", JSON.toJSONString(oxygen));
		oxygenMapper.saveOxygen(oxygen);
	}
	
	/**
	 * 
	 * <p>Title: updateOxygen</p>
	 * <p>Description: </p>
	 * @param oxygen
	 * @see com.xqsight.chronic.service.OxygenService#updateOxygen(Oxygen)
	 */
	@Override
	public void updateOxygen(Oxygen oxygen) {
		logger.debug("出入参数:oxygen={}", JSON.toJSONString(oxygen));
		oxygenMapper.updateOxygen(oxygen);
	}
	
	/**
	 * 
	 * <p>Title: deleteOxygen</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @see com.xqsight.chronic.service.OxygenService#deleteOxygen(Long)
	 */
	@Override
	public void deleteOxygen(Long booldId) {
		logger.debug("出入参数:booldId={}", booldId);
		oxygenMapper.deleteOxygen(booldId);
	}
	
	/**
	 * 
	 * <p>Title: queryOxygen</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.OxygenService#queryOxygen()
	 */
	@Override
	public List<Oxygen> queryOxygen() {
		return oxygenMapper.queryOxygen();
	}
	
	/**
	 * 
	 * <p>Title: queryOxygenById</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @return
	 * @see com.xqsight.chronic.service.OxygenService#queryOxygenById(Long)
	 */
	@Override
	public Oxygen queryOxygenById(Long booldId ){
		logger.debug("出入参数:booldId={}", booldId);
		return oxygenMapper.queryOxygenById(booldId);
	}

	/**
	 * <p>Title: queryOxygen</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.OxygenService#queryOxygenByUser(String)
	 */
	@Override
	public List<Oxygen> queryOxygenByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return oxygenMapper.queryOxygenByUser(createOprId);
	}

	/**
	 * <p>Title: queryOxygen</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.OxygenService#queryOxygenByUserAndDurDay(String, int)
	 */
	@Override
	public List<Oxygen> queryOxygenByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay{}", createOprId,durDay);
		return oxygenMapper.queryOxygenByUserAndDurDay(createOprId, durDay);
	}
	
	
}