<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${basePackage}.${moduleName}.entity.${table.className};

/**
 * <p>${table.remarks} mapper</p>
 *
 * @since ${.now}
 * @author generator
*/
public interface ${table.className}Mapper extends BaseMapper<${table.className}>{
}