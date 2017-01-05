/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>系统字典表实体类</p>
 * <p>Table: sys_dict - --> SysDict</p>
 * <p>系统字典表</p>
 * @since 2017-01-05 06:10:27
 * @author wangganggang
 */
public class SysDict extends Model{

	/** 主键 */
    private Long dictId;

    /** dict_code - 字典编号 */
    private String dictCode;
    /** dict_name - 字典名称 */
    private String dictName;

    public Long getDictId(){
        return this.dictId;
    }
    public void setDictId(Long dictId){
        this.dictId = dictId;
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
    }

    @Override
    public Serializable getPK() {
        return this.dictId;
    }
}