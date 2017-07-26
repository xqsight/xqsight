/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.house.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>预约看房实体类</p>
 * <p>Table: appointment - --> Appointment</p>
 * <p>预约看房</p>
 * @since 2017-07-25 06:39:46
 * @author wangganggang
 */
@Data
public class Appointment extends BaseModel{

	/** 主键 */
    private Long id;

    /** user_name - 用户id */
    private String userName;
    /** user_sex - 0:男，1:女 2:保密 */
    private Byte userSex;
    /** telphone - 电话号码 */
    private String telphone;
    /** associate_type - 0:房屋，1: 房间 */
    private Byte associateType;
    /** associate_id - 关联Id */
    private String associateId;
    /** app_time - 预约时间 */
    private LocalDateTime appTime;
    /** status - 0:申请预约1:管家确认2:预约成功3:取消预约4:预约失败 */
    private Byte status;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}