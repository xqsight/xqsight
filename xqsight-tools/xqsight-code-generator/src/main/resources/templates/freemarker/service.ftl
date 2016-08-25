<#include "copyright.ftl"/>

package ${basePackage}.${moduleName}.service;

import java.util.List;

import ${basePackage}.${moduleName}.model.${table.className};

/**
 * <p>${table.remarks}接口类service</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 */
public interface ${table.className}Service {
	
	/** 保存 **/
	void save${table.className}(${table.className} ${table.javaProperty});
	
	/** 修改 **/
	void update${table.className}(${table.className} ${table.javaProperty});
	
	/** 删除 **/
	void delete${table.className}(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty}</#list>);
	
	/** 根据Id查询 **/
	${table.className} query${table.className}ById(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty}</#list>);
	
	/** 查询列表 **/
	List<${table.className}> query${table.className}();
}