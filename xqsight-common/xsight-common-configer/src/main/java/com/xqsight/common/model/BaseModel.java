/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.model;

import java.util.Date;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月31日 下午9:26:18
 */
public class BaseModel {

	/** 是否有效 0:有效 -1:无效  **/
	private int active = 0;
	/** 创建时间 **/
	private Date createTime = new Date();
	/** 创建人ID **/
	private String createOprId;
	/** 修改时间 **/
	private Date updateTime = new Date();
	/** 修改人ID **/
	private String updOprId;
	/** 备注 **/
	private String remark;

	/**
	 * getter method
	 * @return the active
	 */
	
	public int getActive() {
		return active;
	}
	/**
	 * setter method
	 * @param active the active to set
	 */
	
	public void setActive(int active) {
		this.active = active;
	}
	/**
	 * getter method
	 * @return the createTime
	 */
	
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * setter method
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * getter method
	 * @return the createOprId
	 */
	
	public String getCreateOprId() {
		return createOprId;
	}
	/**
	 * setter method
	 * @param createOprId the createOprId to set
	 */
	
	public void setCreateOprId(String createOprId) {
		this.createOprId = createOprId;
	}
	/**
	 * getter method
	 * @return the updateTime
	 */
	
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * setter method
	 * @param updateTime the updateTime to set
	 */
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * getter method
	 * @return the updOprId
	 */
	
	public String getUpdOprId() {
		return updOprId;
	}
	/**
	 * setter method
	 * @param updOprId the updOprId to set
	 */
	
	public void setUpdOprId(String updOprId) {
		this.updOprId = updOprId;
	}
	/**
	 * getter method
	 * @return the remark
	 */
	
	public String getRemark() {
		return remark;
	}
	/**
	 * setter method
	 * @param remark the remark to set
	 */
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
