/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;
import java.util.Map;

import com.xqsight.chronic.model.GeneVedio;

/**
 * <p>基因大讲堂表接口类service</p>
 * <p>Table: GENE_VEDIO - 基因大讲堂表</p>
 * @since 2016-05-13 03:29:55
 */
public interface GeneVedioService {

	void saveGeneVedio(GeneVedio geneVedio);
	
	void updateGeneVedio(GeneVedio geneVedio);
	
	void deleteGeneVedio(Long vedioId);

	GeneVedio queryGeneVedioById(Long vedioId);
	
	List<GeneVedio> queryGeneVedioByLikeName(String vedioName);

	List<Map<String,Object>> queryGeneVedioWithFirstVedio();
	
}