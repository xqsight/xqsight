/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.tangchao.server.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>问答实体类</p>
 * <p>Table: questions - --> Questions</p>
 * <p>问答</p>
 * @since 2017-07-25 06:40:36
 * @author wangganggang
 */
@Data
public class Questions extends BaseModel{

	/** 主键 */
    private Long id;

    /** question - 问题 */
    private String question;
    /** answer - 回答 */
    private String answer;
    /** type - 类型 */
    private String type;

    @Override
    public Serializable getPK() {
        return this.id;
    }
}