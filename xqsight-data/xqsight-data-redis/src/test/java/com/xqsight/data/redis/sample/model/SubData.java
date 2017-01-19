/**
 * 
 */
package com.xqsight.data.redis.sample.model;

import java.io.Serializable;

/**
 *
 */
public class SubData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -53163428309154830L;
	
	private String dataId;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}
