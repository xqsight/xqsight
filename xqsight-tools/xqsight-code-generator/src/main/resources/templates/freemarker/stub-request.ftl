<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.request;

import io.swagger.v3.oas.annotations.media.Schema;
<#if (table.hasDateColumn)>
import java.util.LocalDateTime;
</#if>
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>${table.remarks} 新增修改 request</p>
 *
 * @since ${.now}
 * @author generator
 */
@Getter
@Setter
@ToString
@Schema(description = "${table.remarks}")
public class ${table.className}Request {

<#list table.primaryKeys as key>
    @Schema(description = "主键id")
    private ${key.javaType} ${key.javaProperty};

</#list>

<#list table.baseColumns as column>
 <#if notNeed?seq_contains(column.javaProperty)><#else>
    @Schema(description = "${column.remarks}")
    private ${column.javaType} ${column.javaProperty};

 </#if>
</#list>
}