/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.AbstractTreeModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>系统字典表实体类</p>
 * <p>Table: sys_dict - --> SysDict</p>
 * <p>系统字典表</p>
 * @since 2017-04-06 09:53:25
 * @author wangganggang
 */
@Data
public class SysDict extends AbstractTreeModel<SysDict>{

	/** 主键 */
    private Long dictId;

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

    public void setDictName(String dictName){
        super.setName(dictName);
        this.dictName = dictName;
    }

    @Override
    public Serializable getPK() {
        return this.dictId;
    }
}