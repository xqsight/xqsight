/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>标签表实体类</p>
 * <p>Table: cms_tag - --> CmsTag</p>
 * <p>标签表</p>
 * @since 2017-02-23 04:52:38
 * @author wangganggang
 */
public class CmsTag extends Model{

	/** 主键 */
    private Long tagId;

    /** tag_name - 标签名称 */
    private String tagName;

    public Long getTagId(){
        return this.tagId;
    }
    public void setTagId(Long tagId){
        this.tagId = tagId;
    }
	public String getTagName(){
        return this.tagName;
    }
    public void setTagName(String tagName){
        this.tagName = tagName;
    }

    @Override
    public Serializable getPK() {
        return this.tagId;
    }
}