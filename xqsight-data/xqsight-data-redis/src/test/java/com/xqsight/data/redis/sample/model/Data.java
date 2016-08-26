/**
 * 
 */
package com.xqsight.data.redis.sample.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author linhaoran
 *
 */
public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6869329344855333978L;
	
	private String dataId;
	
	private List<SubData> list;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public List<SubData> getList() {
		return list;
	}

	public void setList(List<SubData> list) {
		this.list = list;
	}
	
	
	
	
}
