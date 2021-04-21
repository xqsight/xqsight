<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.request;

import com.billbear.common.request.BaseRequest;
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
public class ${table.className}SearchRequest extends BaseRequest {

<#list table.baseColumns as column>
     <#if notNeed?seq_contains(column.javaProperty)><#else>
          @Schema(description = "${column.remarks}")
          private ${column.javaType} ${column.javaProperty};

     </#if>
</#list>
}