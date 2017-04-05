<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.controller;

import com.xqsight.common.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${basePackage}.${moduleName}.model.${table.className};
import ${basePackage}.${moduleName}.service.${table.className}Service;

/**
 * <p>${table.remarks} controller</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 * @author wangganggang
 */
@RestController
@RequestMapping("/${table.controllerPath}")
public class ${table.className}Controller extends BaseController<${table.className}Service,${table.className},Long> {

}