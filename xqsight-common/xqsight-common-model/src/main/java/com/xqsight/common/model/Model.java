package com.xqsight.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Model implements Serializable {

	private static final long serialVersionUID = 5859337383077120660L;

	public abstract Serializable getPK();

	/** 是否有效 0:有效 -1:无效  **/
	private int active = 0;
	/** 创建时间 **/
	private LocalDateTime createTime;
	/** 创建人ID **/
	private String createUserId;
	/** 修改时间 **/
	private LocalDateTime updateTime;
	/** 修改人ID **/
	private String updateUserId;
	/** 备注 **/
	private String remark;

	public int getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
