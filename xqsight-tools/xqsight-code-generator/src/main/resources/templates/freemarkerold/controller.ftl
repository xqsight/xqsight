<#include "copyright.ftl"/>

package ${basePackage}.${moduleName}.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.MessageSupport;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.SSOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${basePackage}.${moduleName}.model.${table.className};
import ${basePackage}.${moduleName}.service.${table.className}Service;

/**
 * <p>${table.remarks} controller</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 */
@RestController
@RequestMapping("/${table.controllerPath}/")
public class ${table.className}Controller{

	@Autowired
	private ${table.className}Service ${table.javaProperty}Service;

	@RequestMapping("save")
	public Object save${table.className}(${table.className} ${table.javaProperty}) {
		${table.javaProperty}.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		${table.javaProperty}Service.save${table.className}(${table.javaProperty});
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object update${table.className}(${table.className} ${table.javaProperty}) {
		${table.javaProperty}.setUpdOprId(SSOUtils.getCurrentUserId().toString());
		${table.javaProperty}Service.update${table.className}(${table.javaProperty});
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object delete${table.className}(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty}</#list>) {
		${table.javaProperty}Service.delete${table.className}(<#list table.primaryKeys as key>${key.javaProperty}</#list>);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("querybyid")
	public Object query${table.className}(<#list table.primaryKeys as key>${key.javaType} ${key.javaProperty}</#list>) {
		${table.className} ${table.javaProperty} = ${table.javaProperty}Service.query${table.className}ById(<#list table.primaryKeys as key>${key.javaProperty}</#list>);
		return MessageSupport.successDataMsg(${table.javaProperty}, "查询成功");
	}
	
	@RequestMapping("query")
	public Object query${table.className}(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<${table.className}> ${table.javaProperty}s = ${table.javaProperty}Service.query${table.className}();
		 xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, ${table.javaProperty}s);
	}
}