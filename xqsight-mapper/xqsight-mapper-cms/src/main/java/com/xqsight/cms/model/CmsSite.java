/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>站点表实体类</p>
 * <p>Table: cms_site - --> CmsSite</p>
 * <p>站点表</p>
 * @since 2017-02-23 04:52:30
 * @author wangganggang
 */
public class CmsSite extends TreeBaseModel<CmsSite>{

	/** 主键 */
    private Long siteId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** site_code - 站点编号 */
    private String siteCode;
    /** site_name - 站点名称 */
    private String siteName;

    public Long getSiteId(){
        return this.siteId;
    }
    public void setSiteId(Long siteId){
        this.siteId = siteId;
        super.setId("" + siteId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getSiteCode(){
        return this.siteCode;
    }
    public void setSiteCode(String siteCode){
        this.siteCode = siteCode;
    }
	public String getSiteName(){
        return this.siteName;
    }
    public void setSiteName(String siteName){
        this.siteName = siteName;
        super.setName(siteName);
    }

    @Override
    public Serializable getPK() {
        return this.siteId;
    }
}