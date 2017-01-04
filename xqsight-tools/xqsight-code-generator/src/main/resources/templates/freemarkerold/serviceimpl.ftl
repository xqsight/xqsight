<#include "copyright.ftl"/>

package ${basePackage}.${moduleName}.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import ${basePackage}.${moduleName}.model.${table.className};
import ${basePackage}.${moduleName}.mapper.${table.className}Mapper;
import ${basePackage}.${moduleName}.service.${table.className}Service;


/**
 * <p>${table.remarks}接口实现类service</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 */
 @Service
public class ${table.className}ServiceImpl implements ${table.className}Service {

	private static Logger logger = LogManager.getLogger(${table.className}ServiceImpl.class); 
	
	@Autowired
	private ${table.className}Mapper ${table.javaProperty}Mapper;

	@Override
	public void save${table.className}(${table.className} ${table.javaProperty}){
		logger.debug("出入参数:${table.javaProperty}＝{}", JSON.toJSONString(${table.javaProperty}));
		${table.javaProperty}Mapper.save${table.className}(${table.javaProperty});
	}
	
	@Override
	public void update${table.className}(${table.className} ${table.javaProperty}) {
		logger.debug("出入参数:${table.javaProperty}＝{}", JSON.toJSONString(${table.javaProperty}));
		${table.javaProperty}Mapper.update${table.className}(${table.javaProperty});
	}
	
	@Override
	public void delete${table.className}(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty}</#list>) {
		logger.debug("出入参数:<#list table.primaryKeys as key>${key.javaProperty}</#list>＝{}", <#list table.primaryKeys as key>${key.javaProperty}</#list>);
		${table.javaProperty}Mapper.delete${table.className}(<#list table.primaryKeys as key>${key.javaProperty}</#list>);
	}
	
	@Override
	public ${table.className} query${table.className}ById(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty} </#list>){
		return ${table.javaProperty}Mapper.query${table.className}ById(<#list table.primaryKeys as key>${key.javaProperty}</#list>);
	}
	
	@Override
	public List<${table.className}> query${table.className}() {
		return ${table.javaProperty}Mapper.query${table.className}();
	}
}