<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.facade.impl;

import com.billbear.common.response.PageData;
import com.billbear.common.response.ResponseData;
import ${basePackage}.${moduleName}.service.${table.className}Service;
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.stub.facade.${table.className}Feign;
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import ${basePackage}.${moduleName}.stub.request.${table.className}SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>${table.remarks} controller</p>
 *
 * @since ${.now}
 * @author generator
*/
@RestController
public class ${table.className}FacadeImpl implements ${table.className}Feign {

    @Autowired
    private ${table.className}Service ${table.javaProperty}Service;

    @Override
    public ResponseData<Boolean> add(${table.className}Request request){
        return ResponseData.SUCCESS(${table.javaProperty}Service.add(request));
    }

    @Override
    public ResponseData<Boolean> upd(${table.className}Request request){
        return ResponseData.SUCCESS(${table.javaProperty}Service.upd(request));
    }

    @Override
    public ResponseData<Boolean> del(String id){
        return ResponseData.SUCCESS(${table.javaProperty}Service.del(id));
    }

    @Override
    public ResponseData<Boolean> delByIds(List<String> ids){
        return ResponseData.SUCCESS(${table.javaProperty}Service.delByIds(ids));
    }

    @Override
    public ResponseData<${table.className}DTO> getById(String id){
        return ResponseData.SUCCESS(${table.javaProperty}Service.getOneById(id));
    }

    @Override
    public ResponseData<PageData<${table.className}DTO>> search(${table.className}SearchRequest request){
        return ResponseData.SUCCESS(${table.javaProperty}Service.search(request));
    }

    @Override
    public ResponseData<List<${table.className}DTO>> list(${table.className}SearchRequest request){
        return ResponseData.SUCCESS(${table.javaProperty}Service.getList(request));
    }


    @Override
    public ResponseData<List<${table.className}DTO>> listByIds(List<String> ids){
        return ResponseData.SUCCESS(${table.javaProperty}Service.getListByIds(ids));
    }
}