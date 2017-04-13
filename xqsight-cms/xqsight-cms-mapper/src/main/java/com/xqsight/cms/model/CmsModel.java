/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.AbstractTreeModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>模块表实体类</p>
 * <p>Table: cms_model - --> CmsModel</p>
 * <p>模块表</p>
 * @since 2017-03-13 11:41:56
 * @author wangganggang
 */
@Data
public class CmsModel extends AbstractTreeModel<CmsModel> {

	/** 主键 */
    private Long modelId;

    /** site_id - 站点内码 */
    private Long siteId;
    /** parent_ids - 所有上级 */
    private String parentIds;
    /** model_code - 模块编号 */
    private String modelCode;
    /** model_name - 模块名称 */
    private String modelName;
    /** model_type - 模块类型 */
    private Byte modelType;
    /** model_url - 模块URl */
    private String modelUrl;
    /** model_desp - 模块说明 */
    private String modelDesp;

    public void setModelName(String modelName){
        this.modelName = modelName;
        super.setName(modelName);
    }

    @Override
    public Serializable getPK() {
        return this.modelId;
    }
}