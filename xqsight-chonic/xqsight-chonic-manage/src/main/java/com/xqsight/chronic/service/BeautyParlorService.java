/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;
import java.util.Map;

import com.xqsight.chronic.model.BeautyParlor;

/**
 * <p>美容院表接口类service</p>
 * <p>Table: BEAUTY_PARLOR - 美容院表</p>
 * @since 2016-05-09 07:48:43
 */
public interface BeautyParlorService {

	/**
	 * 保存
	 * @param beautyParlor
     */
	void saveBeautyParlor(BeautyParlor beautyParlor);

	/**
	 * 修改
	 * @param beautyParlor
     */
	void updateBeautyParlor(BeautyParlor beautyParlor);

	/**
	 * 删除
	 * @param beautyId
     */
	void deleteBeautyParlor(Long beautyId);

	/**
	 * 查询
	 * @return
     */
	List<BeautyParlor> queryBeautyParlor();

	/**
	 * 查询
	 * @param beautyAddress
	 * @return
     */
	List<Map<String,Object>> queryBeautyParlorWithFirstPic(String beautyAddress);

	/**
	 * 查询单挑
	 * @param beautyId
	 * @return
     */
	BeautyParlor queryBeautyParlorById(Long beautyId);
	
}