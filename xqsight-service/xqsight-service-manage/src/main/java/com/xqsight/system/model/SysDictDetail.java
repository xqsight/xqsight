/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>系统字典明细表实体类</p>
 * <p>Table: sys_dict_detail - --> SysDictDetail</p>
 * <p>系统字典明细表</p>
 * @since 2017-01-05 06:10:32
 * @author wangganggang
 */
public class SysDictDetail extends Model{

	/** 主键 */
    private Long dictDetailId;

    /** dict_id - 字典编号 */
    private Long dictId;
    /** dict_value - 字典值 */
    private String dictValue;
    /** dict_desp - 字典描述 */
    private String dictDesp;
    /** editable - 是否可编辑0:有效-1:无效 */
    private Integer editable;
    /** dict_lang - 语言 */
    private String dictLang;

    public Long getDictDetailId(){
        return this.dictDetailId;
    }
    public void setDictDetailId(Long dictDetailId){
        this.dictDetailId = dictDetailId;
    }
	public Long getDictId(){
        return this.dictId;
    }
    public void setDictId(Long dictId){
        this.dictId = dictId;
    }
	public String getDictValue(){
        return this.dictValue;
    }
    public void setDictValue(String dictValue){
        this.dictValue = dictValue;
    }
	public String getDictDesp(){
        return this.dictDesp;
    }
    public void setDictDesp(String dictDesp){
        this.dictDesp = dictDesp;
    }
	public Integer getEditable(){
        return this.editable;
    }
    public void setEditable(Integer editable){
        this.editable = editable;
    }
	public String getDictLang(){
        return this.dictLang;
    }
    public void setDictLang(String dictLang){
        this.dictLang = dictLang;
    }

    @Override
    public Serializable getPK() {
        return this.dictDetailId;
    }
}