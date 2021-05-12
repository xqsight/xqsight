<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service.convert;

import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.entity.${table.className};
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

<#if notNeed?seq_contains(column.javaProperty)><#else>
<#if (table.hasBigDecimalColumn)>
import java.math.BigDecimal;
</#if>
</#if>

/**
 * <p>${table.remarks} convert</p>
 * <p>Table: ${table.tableName} - --> ${table.className}</p>
 *
 * @since ${.now}
 * @author generator
*/
@Mapper
public interface ${table.className}ConvertMapper {

    ${table.className}ConvertMapper INSTANCE = Mappers.getMapper(${table.className}ConvertMapper.class);

    /**
     * 转DTO
     *
     * @param ${table.javaProperty}
     * @return
    */
    ${table.className}DTO entityToDTO(${table.className} ${table.javaProperty});


    /**
     * request to entity
     *
     * @param request
     * @return
    */
    ${table.className} ${table.javaProperty}RequestToEntity(${table.className}Request request);

}