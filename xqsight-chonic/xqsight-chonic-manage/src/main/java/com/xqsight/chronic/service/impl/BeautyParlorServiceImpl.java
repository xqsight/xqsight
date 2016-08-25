/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.model.BeautyParlor;
import com.xqsight.chronic.mysqlmapper.BeautyParlorMapper;
import com.xqsight.chronic.service.BeautyParlorService;


/**
 * <p>美容院表接口实现类service</p>
 * <p>Table: BEAUTY_PARLOR - 美容院表</p>
 * @since 2016-05-09 07:48:43
 */
 @Service
public class BeautyParlorServiceImpl implements BeautyParlorService {

	private static Logger logger = LogManager.getLogger(BeautyParlorServiceImpl.class); 
	
	@Autowired
	private BeautyParlorMapper beautyParlorMapper;

	@Override
	public void saveBeautyParlor(BeautyParlor beautyParlor){
		logger.debug("出入参数:{}", JSON.toJSONString(beautyParlor));
		beautyParlorMapper.saveBeautyParlor(beautyParlor);
	}
	
	@Override
	public void updateBeautyParlor(BeautyParlor beautyParlor) {
		logger.debug("出入参数:{}", JSON.toJSONString(beautyParlor));
		beautyParlorMapper.updateBeautyParlor(beautyParlor);
	}
	
	@Override
	public void deleteBeautyParlor(Long beautyId) {
		logger.debug("出入参数:{}", beautyId);
		beautyParlorMapper.deleteBeautyParlor(beautyId);
	}
	
	@Override
	public List<BeautyParlor> queryBeautyParlor() {
		return beautyParlorMapper.queryBeautyParlor();
	}
	
	@Override
	public BeautyParlor queryBeautyParlorById(Long beautyId ){
		logger.debug("出入参数:{}", beautyId);
		return beautyParlorMapper.queryBeautyParlorById(beautyId);
	}

	@Override
	public List<Map<String, Object>> queryBeautyParlorWithFirstPic(String beautyAddress) {
		logger.debug("出入参数:beautyAddress={}", beautyAddress);
		return beautyParlorMapper.queryBeautyParlorWithFirstPic(beautyAddress);
	}
}