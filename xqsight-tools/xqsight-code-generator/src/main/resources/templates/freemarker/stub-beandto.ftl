<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.bean;

import io.swagger.v3.oas.annotations.media.Schema;
<#if notNeed?seq_contains(column.javaProperty)><#else>
<#if (table.hasDateColumn)>
import java.time.LocalDateTime;
</#if>
</#if>
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>${table.remarks}</p>
 * <p>Table: ${table.tableName} - --> ${table.className}</p>
 * @since ${.now}
 * @author generator
*/
@Getter
@Setter
@ToString
public class ${table.className}DTO {

<#list table.baseColumns as column>
<#if notNeed?seq_contains(column.javaProperty)><#else>
    @Schema(description = "${column.remarks}")
    private ${column.javaType} ${column.javaProperty};

</#if>
</#list>
}