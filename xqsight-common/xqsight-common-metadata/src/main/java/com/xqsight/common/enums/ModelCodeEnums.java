/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.common.enums;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年5月18日 上午10:31:30
 *
 */
public enum ModelCodeEnums {
	
	/**
	 * 基因百科
	 */
	GENES_DESC(1,1), 
	
	/**
	 *  资讯
	 */
	GENE_NEW(1,2),
	
	/**
	 * 社区论坛
	 */
	FORUM(1,3),
	
	/**
	 * 咨询
	 */
	GENE_ASK(1,4),
	
	/**
	 * 有问必答
	 */
	QUESTION(2,5),
	
	/**
	 * 糖尿病
	 */
	GDM_MANAGE(2,6),
	
	/**
	 * 高血压
	 */
	HBP_MANAGE(2,7),
	
	/**
	 * 心脏病
	 */
	HEART_MANAGE(2,8),
	
	/**
	 * 肿瘤
	 */
	TUMOUR_MANAGE(2,9),
	
	/**
	 * 肝病
	 */
	LIVE_MANAGE(2,10),
	
	/**
	 * 脑卒中
	 */
	BRAIN_STROKE(2,11),
	
	/**
	 * 失眠
	 */
	LOSE_SLEEP(2,12),
	
	/**
	 * 慢阻肺
	 */
	COPO_MANAGE(2,13),
	
	/**
	 * 首页轮播内容
	 */
	APP_GENE_FRIST(1,14),
	
	/**
	 * 首页轮播内容
	 */
	APP_MANAGE_FRIST(2,15),

	/**
	 * 消息
	 */
	APP_GENE_NEW(1,16),

	/**
	 * 消息
	 */
	APP_MANAGE_NEW(1,17),

	/**
	 * 推荐
	 */
	BEST_NEW(2,18);

	private int appId;
	
	private int modelId;

	private ModelCodeEnums(int appId,int modelId) {
		this.appId = appId;
		this.modelId = modelId;
	}

	/**
	 * getter method
	 * @return the appId
	 */
	public int getAppId() {
		return appId;
	}

	/**
	 * getter method
	 * @return the modelId
	 */
	public int getModelId() {
		return modelId;
	}
	
	public static ModelCodeEnums getEnum(String modelCode){
		return ModelCodeEnums.valueOf(modelCode);
	}
	
}
