package com.xqsight.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by wangganggang on 2017/3/23.
 */
public abstract class BaseModel implements Serializable {

    private static final long serialVersionUID = 5859337383077120660L;

    public abstract Serializable getPK();

    /**
     * 是否有效 0:有效 -1:无效
     **/
    private byte active = 0;
    /**
     * 创建时间
     **/
    private LocalDateTime createTime;
    /**
     * 创建人ID
     **/
    private String createUserId;
    /**
     * 修改时间
     **/
    private LocalDateTime updateTime;
    /**
     * 修改人ID
     **/
    private String updateUserId;
    /**
     * 备注
     **/
    private String remark;

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
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


    public boolean isNoActive() {
        return this.active == -1;
    }
}
