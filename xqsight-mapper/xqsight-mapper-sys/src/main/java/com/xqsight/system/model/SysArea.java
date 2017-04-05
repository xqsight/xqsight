/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.AbstractTreeModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>区域表实体类</p>
 * <p>Table: sys_area - --> SysArea</p>
 * <p>区域表</p>
 * @since 2017-04-05 05:16:47
 * @author wangganggang
 */
@Data
public class SysArea extends AbstractTreeModel<SysArea> {

	/** 主键 */
    private Long areaId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** area_name - 区域名称 */
    private String areaName;
    /** area_code - 区域编码 */
    private String areaCode;
    /** area_type - 区域类型 */
    private Byte areaType;
    /** icon - 图标 */
    private String icon;
    /** sort - 排序 */
    private Short sort;

    @Override
    public Serializable getPK() {
        return this.areaId;
    }
}