/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.server.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>消息实体类</p>
 * <p>Table: message - --> Message</p>
 * <p>消息</p>
 * @since 2017-07-25 06:40:28
 * @author wangganggang
 */
@Data
public class Message extends BaseModel{

	/** 主键 */
    private Long id;

    /** title - 消息标题 */
    private String title;
    /** content - 消息内容 */
    private String content;
    /** type - 消息类型 */
    private String type;
    /** sender - 发送人 */
    private String sender;
    /** receiver - 接收人 */
    private String receiver;
    /** status - 状态 */
    private String status;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}