<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import ${basePackage}.${moduleName}.model.${table.className};
import ${basePackage}.${moduleName}.service.${table.className}Service;
import ${basePackage}.${moduleName}.mapper.${table.className}Mapper;

/**
 * <p>${table.remarks}实现类 service</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 * @author wangganggang
 */
@Service
public class ${table.className}ServiceImpl extends AbstractCrudService<${table.className}Mapper,${table.className}, Long> implements ${table.className}Service {

}