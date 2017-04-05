<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

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
@Data
public class ${table.className} extends BaseModel{

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

    @Override
    public Serializable getPK() {
        return this.<#list table.primaryKeys as key>${key.javaProperty}</#list>;
    }
}