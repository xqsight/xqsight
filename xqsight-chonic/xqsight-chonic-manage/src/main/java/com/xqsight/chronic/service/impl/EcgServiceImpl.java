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
import com.xqsight.chronic.model.Ecg;
import com.xqsight.chronic.mysqlmapper.EcgMapper;
import com.xqsight.chronic.service.EcgService;


/**
 * <p>心电记录表接口实现类service</p>
 * <p>Table: ECG - 心电记录表</p>
 * @since 2016-05-09 07:50:46
 */
 @Service
public class EcgServiceImpl implements EcgService {

	private static Logger logger = LogManager.getLogger(EcgServiceImpl.class); 
	
	@Autowired
	private EcgMapper ecgMapper;

	/**
	 * 
	 * <p>Title: saveEcg</p>
	 * <p>Description: </p>
	 * @param ecg
	 * @see com.xqsight.chronic.service.EcgService#saveEcg(Ecg)
	 */
	@Override
	public void saveEcg(Ecg ecg){
		logger.debug("出入参数:ecg={}", JSON.toJSONString(ecg));
		ecgMapper.saveEcg(ecg);
	}
	
	/**
	 * 
	 * <p>Title: updateEcg</p>
	 * <p>Description: </p>
	 * @param ecg
	 * @see com.xqsight.chronic.service.EcgService#updateEcg(Ecg)
	 */
	@Override
	public void updateEcg(Ecg ecg) {
		logger.debug("出入参数:ecg={}", JSON.toJSONString(ecg));
		ecgMapper.updateEcg(ecg);
	}
	
	/**
	 * 
	 * <p>Title: deleteEcg</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @see com.xqsight.chronic.service.EcgService#deleteEcg(Long)
	 */
	@Override
	public void deleteEcg(Long booldId) {
		logger.debug("出入参数:booldId={}", booldId);
		ecgMapper.deleteEcg(booldId);
	}
	
	/**
	 * 
	 * <p>Title: queryEcg</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.EcgService#queryEcg()
	 */
	@Override
	public List<Ecg> queryEcg() {
		return ecgMapper.queryEcg();
	}
	
	/**
	 * 
	 * <p>Title: queryEcgById</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @return
	 * @see com.xqsight.chronic.service.EcgService#queryEcgById(Long)
	 */
	@Override
	public Ecg queryEcgById(Long booldId ){
		logger.debug("出入参数:booldId={}", booldId);
		return ecgMapper.queryEcgById(booldId);
	}

	/**
	 * <p>Title: queryEcg</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.EcgService#queryEcgByUser(String)
	 */
	@Override
	public List<Ecg> queryEcgByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return ecgMapper.queryEcgByUser(createOprId);
	}

	/**
	 * <p>Title: queryEcg</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.EcgService#queryEcgByUserAndDurDay(String, int)
	 */
	@Override
	public List<Ecg> queryEcgByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay={}", createOprId,durDay);
		return ecgMapper.queryEcgByUserAndDurDay(createOprId, durDay);
	}
	
	
}