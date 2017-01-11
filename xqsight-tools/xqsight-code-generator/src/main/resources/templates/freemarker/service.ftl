<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service.impl;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.${moduleName}.model.${table.className};
import ${basePackage}.${moduleName}.mapper.${table.className}Mapper;


/**
 * <p>${table.remarks}实现类service</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 * @author wangganggang
 */
@Service
public class ${table.className}Service extends DefaultEntityService<${table.className}, Long> {

	@Autowired
	private ${table.className}Mapper ${table.javaProperty}Mapper;

	@Override
	protected Dao<${table.className}, Long> getDao() {
		return ${table.javaProperty}Mapper;
	}
}