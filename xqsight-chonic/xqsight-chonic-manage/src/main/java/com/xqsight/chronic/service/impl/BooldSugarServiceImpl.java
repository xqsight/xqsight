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
import com.xqsight.chronic.model.BooldSugar;
import com.xqsight.chronic.mysqlmapper.BooldSugarMapper;
import com.xqsight.chronic.service.BooldSugarService;


/**
 * <p>血糖记录表接口实现类service</p>
 * <p>Table: BOOLD_SUGAR - 血糖记录表</p>
 * @since 2016-05-09 07:50:39
 */
 @Service
public class BooldSugarServiceImpl implements BooldSugarService {

	private static Logger logger = LogManager.getLogger(BooldSugarServiceImpl.class); 
	
	@Autowired
	private BooldSugarMapper booldSugarMapper;

	/**
	 * 
	 * <p>Title: saveBooldSugar</p>
	 * <p>Description: </p>
	 * @param booldSugar
	 * @see com.xqsight.chronic.service.BooldSugarService#saveBooldSugar(BooldSugar)
	 */
	@Override
	public void saveBooldSugar(BooldSugar booldSugar){
		logger.debug("出入参数:booldSugar={}", JSON.toJSONString(booldSugar));
		booldSugarMapper.saveBooldSugar(booldSugar);
	}
	
	/**
	 * 
	 * <p>Title: updateBooldSugar</p>
	 * <p>Description: </p>
	 * @param booldSugar
	 * @see com.xqsight.chronic.service.BooldSugarService#updateBooldSugar(BooldSugar)
	 */
	@Override
	public void updateBooldSugar(BooldSugar booldSugar) {
		logger.debug("出入参数:booldSugar={}", JSON.toJSONString(booldSugar));
		booldSugarMapper.updateBooldSugar(booldSugar);
	}
	
	/**
	 * 
	 * <p>Title: deleteBooldSugar</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @see com.xqsight.chronic.service.BooldSugarService#deleteBooldSugar(Long)
	 */
	@Override
	public void deleteBooldSugar(Long booldId) {
		logger.debug("出入参数:booldId={}", booldId);
		booldSugarMapper.deleteBooldSugar(booldId);
	}
	
	/**
	 * 
	 * <p>Title: queryBooldSugar</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.BooldSugarService#queryBooldSugar()
	 */
	@Override
	public List<BooldSugar> queryBooldSugar() {
		return booldSugarMapper.queryBooldSugar();
	}
	
	/**
	 * 
	 * <p>Title: queryBooldSugarById</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @return
	 * @see com.xqsight.chronic.service.BooldSugarService#queryBooldSugarById(Long)
	 */
	@Override
	public BooldSugar queryBooldSugarById(Long booldId ){
		logger.debug("出入参数:booldId={}", booldId);
		return booldSugarMapper.queryBooldSugarById(booldId);
	}

	/**
	 * <p>Title: queryBooldSugar</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.BooldSugarService#queryBooldSugarByUser(String)
	 */
	@Override
	public List<BooldSugar> queryBooldSugarByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return booldSugarMapper.queryBooldSugarByUser(createOprId);
	}

	/**
	 * <p>Title: queryBooldSugar</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.BooldSugarService#queryBooldSugarByUserAndDurDay(String, int)
	 */
	@Override
	public List<BooldSugar> queryBooldSugarByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay={}", createOprId,durDay);
		return booldSugarMapper.queryBooldSugarByUserAndDurDay(createOprId, durDay);
	}
	
	
}