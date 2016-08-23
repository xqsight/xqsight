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
public enum AppCodeEnums {
	/**
	 * 慢病基因
	 */
	CHRONIC_GENE(1),
	/**
	 * 慢病管理
	 */
	CHRONIC_MANAGE(2);

	private int appId;

	private AppCodeEnums(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public static AppCodeEnums getEnum(String appCode) {
		return AppCodeEnums.valueOf(appCode);
	}
}
