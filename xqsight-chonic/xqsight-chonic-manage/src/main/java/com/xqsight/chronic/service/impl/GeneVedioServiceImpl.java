/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.model.GeneVedio;
import com.xqsight.chronic.mysqlmapper.GeneVedioMapper;
import com.xqsight.chronic.service.GeneVedioService;


/**
 * <p>基因大讲堂表接口实现类service</p>
 * <p>Table: GENE_VEDIO - 基因大讲堂表</p>
 * @since 2016-05-13 03:29:55
 */
 @Service
public class GeneVedioServiceImpl implements GeneVedioService {

	private static Logger logger = LogManager.getLogger(GeneVedioServiceImpl.class); 
	
	@Autowired
	private GeneVedioMapper geneVedioMapper;

	@Override
	public void saveGeneVedio(GeneVedio geneVedio){
		logger.debug("出入参数:{}", JSON.toJSONString(geneVedio));
		geneVedioMapper.saveGeneVedio(geneVedio);
	}
	
	@Override
	public void updateGeneVedio(GeneVedio geneVedio) {
		logger.debug("出入参数:{}", JSON.toJSONString(geneVedio));
		geneVedioMapper.updateGeneVedio(geneVedio);
	}
	
	@Override
	public void deleteGeneVedio(Long vedioId) {
		logger.debug("出入参数:{}", vedioId);
		geneVedioMapper.deleteGeneVedio(vedioId);
	}
	
	@Override
	public List<GeneVedio> queryGeneVedioByLikeName(String vedioName) {
		if(StringUtils.isEmpty(vedioName))
			vedioName = "";
		return geneVedioMapper.queryGeneVedioByLikeName(vedioName);
	}
	
	@Override
	public GeneVedio queryGeneVedioById(Long vedioId ){
		return geneVedioMapper.queryGeneVedioById(vedioId);
	}


	@Override
	public List<Map<String,Object>> queryGeneVedioWithFirstVedio() {
		return geneVedioMapper.queryGeneVedioWithFirstVedio();
	}
}