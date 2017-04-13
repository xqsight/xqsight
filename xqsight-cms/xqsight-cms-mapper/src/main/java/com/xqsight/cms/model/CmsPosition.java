/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.AbstractTreeModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>职位表实体类</p>
 * <p>Table: cms_position - --> CmsPosition</p>
 * <p>职位表</p>
 * @since 2017-02-23 04:52:22
 * @author wangganggang
 */
@Data
public class CmsPosition extends AbstractTreeModel<CmsPosition> {

	/** 主键 */
    private Long positionId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** position_name - 职位名称 */
    private String positionName;
    /** position_code - 职位编码 */
    private String positionCode;

    public void setPositionName(String positionName){
        this.positionName = positionName;
        super.setName(positionName);
    }

    @Override
    public Serializable getPK() {
        return this.positionId;
    }
}