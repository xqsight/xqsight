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
import com.xqsight.chronic.model.Fat;
import com.xqsight.chronic.mysqlmapper.FatMapper;
import com.xqsight.chronic.service.FatService;


/**
 * <p>脂肪记录表接口实现类service</p>
 * <p>Table: FAT - 脂肪记录表</p>
 * @since 2016-05-09 07:50:53
 */
 @Service
public class FatServiceImpl implements FatService {

	private static Logger logger = LogManager.getLogger(FatServiceImpl.class); 
	
	@Autowired
	private FatMapper fatMapper;

	/**
	 * 
	 * <p>Title: saveFat</p>
	 * <p>Description: </p>
	 * @param fat
	 * @see com.xqsight.chronic.service.FatService#saveFat(Fat)
	 */
	@Override
	public void saveFat(Fat fat){
		logger.debug("出入参数:fat={}", JSON.toJSONString(fat));
		fatMapper.saveFat(fat);
	}
	
	/**
	 * 
	 * <p>Title: updateFat</p>
	 * <p>Description: </p>
	 * @param fat
	 * @see com.xqsight.chronic.service.FatService#updateFat(Fat)
	 */
	@Override
	public void updateFat(Fat fat) {
		logger.debug("出入参数:fat={}", JSON.toJSONString(fat));
		fatMapper.updateFat(fat);
	}
	
	/**
	 * 
	 * <p>Title: deleteFat</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @see com.xqsight.chronic.service.FatService#deleteFat(Long)
	 */
	@Override
	public void deleteFat(Long booldId) {
		logger.debug("出入参数:booldId={}", booldId);
		fatMapper.deleteFat(booldId);
	}
	
	/**
	 * 
	 * <p>Title: queryFat</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.chronic.service.FatService#queryFat()
	 */
	@Override
	public List<Fat> queryFat() {
		return fatMapper.queryFat();
	}
	
	/**
	 * 
	 * <p>Title: queryFatById</p>
	 * <p>Description: </p>
	 * @param booldId
	 * @return
	 * @see com.xqsight.chronic.service.FatService#queryFatById(Long)
	 */
	@Override
	public Fat queryFatById(Long booldId ){
		logger.debug("出入参数:booldId={}", booldId);
		return fatMapper.queryFatById(booldId);
	}

	/**
	 * <p>Title: queryFat</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @return
	 * @see com.xqsight.chronic.service.FatService#queryFatByUser(String)
	 */
	@Override
	public List<Fat> queryFatByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return fatMapper.queryFatByUser(createOprId);
	}

	/**
	 * <p>Title: queryFat</p>
	 * <p>Description: </p>
	 * @param createOprId
	 * @param durDay
	 * @return
	 * @see com.xqsight.chronic.service.FatService#queryFatByUserAndDurDay(String, int)
	 */
	@Override
	public List<Fat> queryFatByUserAndDurDay(String createOprId, int durDay) {
		logger.debug("出入参数:createOprId={},durDay={}", createOprId,durDay);
		return fatMapper.queryFatByUserAndDurDay(createOprId, durDay);
	}
	
	
}