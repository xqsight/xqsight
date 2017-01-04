<#include "copyright.ftl"/>

package ${basePackage}.${moduleName}.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ${basePackage}.${moduleName}.model.${table.className};

/**
 * <p>${table.remarks}数据库Mapper类</p>
 * <p>${table.remarks}</p>
 * @since ${.now}
 */
public interface ${table.className}Mapper {

	@Insert("${table.insertSql}")
	void save${table.className}(${table.className} ${table.javaProperty});
	
	@Update("${table.updateSql}")
	void update${table.className}(${table.className} ${table.javaProperty});
	
	@Delete("${table.deleteSql}")
	void delete${table.className}(<#list table.primaryKeys as key>@Param("${key.javaProperty}") ${key.javaType} ${key.javaProperty}</#list>);
	
	@Select("${table.querySqlById}")
	${table.className} query${table.className}ById(<#list table.primaryKeys as key>@Param("${key.javaProperty}") ${key.javaType} ${key.javaProperty}</#list>);

	@Select("${table.querySql}")
	List<${table.className}> query${table.className}();
}