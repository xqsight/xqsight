<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service.impl;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.system.model.${table.className};

/**
* <p>${table.remarks} service</p>
* <p>Table: ${table.tableName} - ${table.remarks}</p>
* @since ${.now}
* @author wangganggang
*/
public interface ${table.className}Service extends ICrudService<${table.className}, Long> {

}