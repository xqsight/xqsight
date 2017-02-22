/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>系统字典表实体类</p>
 * <p>Table: sys_dict - --> SysDict</p>
 * <p>系统字典表</p>
 * @since 2017-01-07 11:57:14
 * @author wangganggang
 */
public class SysDict extends TreeBaseModel<SysDict>{

	/** 主键 */
    private Long dictId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** dict_code - 字典编号 */
    private String dictCode;
    /** dict_name - 字典名称 */
    private String dictName;
    /** dict_value - 字典值 */
    private String dictValue;
    /** editable - 是否可编辑 */
    private Integer editable;

    public Long getDictId(){
        return this.dictId;
    }
    public void setDictId(Long dictId){
        this.dictId = dictId;
        super.setId("" + dictId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getDictCode(){
        return this.dictCode;
    }
    public void setDictCode(String dictCode){
        this.dictCode = dictCode;
    }
	public String getDictName(){
        return this.dictName;
    }
    public void setDictName(String dictName){
        this.dictName = dictName;
        super.setName(dictName);
    }
	public String getDictValue(){
        return this.dictValue;
    }
    public void setDictValue(String dictValue){
        this.dictValue = dictValue;
    }
	public Integer getEditable(){
        return this.editable;
    }
    public void setEditable(Integer editable){
        this.editable = editable;
    }

    @Override
    public Serializable getPK() {
        return this.dictId;
    }
}