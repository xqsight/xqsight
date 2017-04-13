/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>标签表实体类</p>
 * <p>Table: cms_tag - --> CmsTag</p>
 * <p>标签表</p>
 * @since 2017-02-23 04:52:38
 * @author wangganggang
 */
@Data
public class CmsTag extends BaseModel {

	/** 主键 */
    private Long tagId;

    /** tag_name - 标签名称 */
    private String tagName;

    @Override
    public Serializable getPK() {
        return this.tagId;
    }
}