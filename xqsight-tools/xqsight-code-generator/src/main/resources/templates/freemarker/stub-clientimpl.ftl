<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.feign.client.impl;

import com.billbear.common.response.PageData;
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.stub.feign.client.${table.className}Client;
import ${basePackage}.${moduleName}.stub.facade.${table.className}Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>${table.remarks} client impl</p>
*
* @since ${.now}
* @author generator
*/
@Component
public class ${table.className}ClientImpl implements ${table.className}Client {

    @Autowired
    private ${table.className}Feign ${table.javaProperty}Feign;


    @Override
    public Boolean add(${table.className}Request request){
        return ${table.javaProperty}Feign.add(request).isSuccessGet();
    }

    @Override
    public Boolean upd(${table.className}Request request){
        return ${table.javaProperty}Feign.upd(request).isSuccessGet();
    }

    @Override
    public Boolean del(String id){
        return ${table.javaProperty}Feign.del(request).isSuccessGet();
    }

    @Override
    public Boolean delByIds(List<String> ids){
        return ${table.javaProperty}Feign.delByIds(request).isSuccessGet();
    }

    @Override
    public ${table.className}DTO getById(String id){
        return ${table.javaProperty}Feign.getById(request).isSuccessGet();
    }

    @Override
    public PageData<${table.className}DTO> search(${table.className}SearchRequest request){
        return ${table.javaProperty}Feign.search(request).isSuccessGet();
    }

    @Override
    public List<${table.className}DTO> list(${table.className}SearchRequest request){
        return ${table.javaProperty}Feign.list(request).isSuccessGet();
    }

    @Override
    public List<${table.className}DTO> listByIds(List<String> ids){
        return ${table.javaProperty}Feign.listByIds(ids).isSuccessGet();
    }
}