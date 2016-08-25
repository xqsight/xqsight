/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;

import com.xqsight.chronic.model.Ecg;

/**
 * <p>心电记录表接口类service</p>
 * <p>Table: ECG - 心电记录表</p>
 * @since 2016-05-09 07:50:46
 */
public interface EcgService {

	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: saveEcg
	 * @param @param ecg    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveEcg(Ecg ecg);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: updateEcg
	 * @param @param ecg    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updateEcg(Ecg ecg);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: deleteEcg
	 * @param @param booldId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteEcg(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryEcg
	 * @param @return    设定文件
	 * @return List<Ecg>    返回类型
	 * @throws
	 */
	List<Ecg> queryEcg();
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryEcgById
	 * @param @param booldId
	 * @param @return    设定文件
	 * @return Ecg    返回类型
	 * @throws
	 */
	Ecg queryEcgById(Long booldId);
	
	/**
	 * 
	 * @Description: TODO
	 *
	 * @Title: queryEcgByUser
	 * @param @param createOprId
	 * @param @return    设定文件
	 * @return List<Ecg>    返回类型
	 * @throws
	 */
	List<Ecg> queryEcgByUser(String createOprId);
	
	/**
	 * 查询最近durday天的数据
	 * @Description: TODO
	 *
	 * @Title: queryEcgByUserAndDurDay
	 * @param @param createOprId
	 * @param @param durDay
	 * @param @return    设定文件
	 * @return List<Ecg>    返回类型
	 * @throws
	 */
	List<Ecg> queryEcgByUserAndDurDay(String createOprId, int durDay);
	
}