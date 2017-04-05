/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>系统字典表实体类</p>
 * <p>Table: sys_dict - --> SysDict</p>
 * <p>系统字典表</p>
 * @since 2017-04-05 05:16:52
 * @author wangganggang
 */
@Data
public class SysDict extends BaseModel{

	/** 主键 */
    private Long dictId;

    /** parent_id - 父级id */
    private Long parentId;
    /** parent_ids - 所有上级 */
    private String parentIds;
    /** sort - 排序 */
    private Short sort;
    /** dict_code - 字典编号 */
    private String dictCode;
    /** dict_name - 字典名称 */
    private String dictName;
    /** dict_value - 字典值 */
    private String dictValue;
    /** editable - 是否可编辑 */
    private Byte editable;

    @Override
    public Serializable getPK() {
        return this.dictId;
    }
}