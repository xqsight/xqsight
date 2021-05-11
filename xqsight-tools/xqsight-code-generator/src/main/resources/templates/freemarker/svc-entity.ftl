<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.billbear.common.entity.BaseEntity;
<#if (table.hasDateColumn)>
import java.time.LocalDateTime;
</#if>
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>${table.remarks}实体类</p>
 * <p>Table: ${table.tableName} - --> ${table.className}</p>
 *
 * @since ${.now}
 * @author generator
 */
@Getter
@Setter
@ToString
public class ${table.className} extends BaseEntity{

<#list table.primaryKeys as key>
	/**
    * 主键
    */
    @TableId(type = IdType.ASSIGN_ID)
    private ${key.javaType} ${key.javaProperty};

</#list>

<#list table.baseColumns as column>
<#if notNeed?seq_contains(column.javaProperty)><#else>
    /**
    * ${column.remarks}
    */
    private ${column.javaType} ${column.javaProperty};

</#if>
</#list>
}