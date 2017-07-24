package com.xqsight.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqsight.common.model.constants.Constants;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangganggang
 * @date 2017/3/23
 */
@Data
public abstract class BaseModel implements Serializable {

    public abstract Serializable getPK();

    /**
     * 是否有效 0:有效 -1:无效
     **/
    private byte active = 0;
    /**
     * 创建时间
     **/
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 创建人ID
     **/
    private String createUserId;
    /**
     * 修改时间
     **/
    private LocalDateTime updateTime = LocalDateTime.now();
    /**
     * 修改人ID
     **/
    private String updateUserId;
    /**
     * 备注
     **/
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public boolean isActive() {
        return this.active == Constants.ACTIVE;
    }

    public Class getPkClass(){
        return Long.class;
    }
}
