/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>快捷键表实体类</p>
 * <p>Table: sys_quick_key - --> SysQuickKey</p>
 * <p>快捷键表</p>
 * @since 2017-01-07 11:57:57
 * @author wangganggang
 */
public class SysQuickKey extends Model{

	/** 主键 */
    private Long id;
	/** 主键 */
    private Integer funOrder;

    /** key_icon - 图标 */
    private String keyIcon;
    /** key_title - 标题 */
    private String keyTitle;
    /** key_value - 连接值 */
    private String keyValue;

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Integer getFunOrder(){
        return this.funOrder;
    }
    public void setFunOrder(Integer funOrder){
        this.funOrder = funOrder;
    }
	public String getKeyIcon(){
        return this.keyIcon;
    }
    public void setKeyIcon(String keyIcon){
        this.keyIcon = keyIcon;
    }
	public String getKeyTitle(){
        return this.keyTitle;
    }
    public void setKeyTitle(String keyTitle){
        this.keyTitle = keyTitle;
    }
	public String getKeyValue(){
        return this.keyValue;
    }
    public void setKeyValue(String keyValue){
        this.keyValue = keyValue;
    }

    @Override
    public Serializable getPK() {
        return this.id;
    }
}