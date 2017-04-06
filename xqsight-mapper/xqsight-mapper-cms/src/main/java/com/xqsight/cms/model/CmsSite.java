/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.AbstractTreeModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>站点表实体类</p>
 * <p>Table: cms_site - --> CmsSite</p>
 * <p>站点表</p>
 * @since 2017-02-23 04:52:30
 * @author wangganggang
 */
@Data
public class CmsSite extends AbstractTreeModel<CmsSite> {

	/** 主键 */
    private Long siteId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** site_code - 站点编号 */
    private String siteCode;
    /** site_name - 站点名称 */
    private String siteName;

    public void setSiteName(String siteName){
        this.siteName = siteName;
        super.setName(siteName);
    }

    @Override
    public Serializable getPK() {
        return this.siteId;
    }
}