<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;

<#if (table.hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>

/**
 * <p>${table.remarks}实体类</p>
 * <p>Table: ${table.tableName} - --> ${table.className}</p>
 * <p>${table.remarks}</p>
 * @since ${.now}
 * @author wangganggang
 */
public class ${table.className} extends Model{

<#list table.primaryKeys as key>
	/** 主键 */
    private ${key.javaType} ${key.javaProperty};
</#list>

<#list table.baseColumns as column>
<#if notNeed?seq_contains(column.javaProperty)><#else>
    /** ${column.columnName} - ${column.remarks} */
    private ${column.javaType} ${column.javaProperty};
</#if>
</#list>

<#list table.primaryKeys as key>
    <#if notNeed?seq_contains(column.javaProperty)><#else>
    public ${key.javaType} ${key.getterMethodName}(){
        return this.${key.javaProperty};
    }
    public void ${key.setterMethodName}(${key.javaType} ${key.javaProperty}){
        this.${key.javaProperty} = ${key.javaProperty};
    }
    </#if>
</#list>
<#list table.baseColumns as column>
    <#if notNeed?seq_contains(column.javaProperty)><#else>
	public ${column.javaType} ${column.getterMethodName}(){
        return this.${column.javaProperty};
    }
    public void ${column.setterMethodName}(${column.javaType} ${column.javaProperty}){
        this.${column.javaProperty} = ${column.javaProperty};
    }
    </#if>
</#list>

    @Override
    public Serializable getPK() {
        return this.<#list table.primaryKeys as key>${key.javaProperty}</#list>;
    }
}