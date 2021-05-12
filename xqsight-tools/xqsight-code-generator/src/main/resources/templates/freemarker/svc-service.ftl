<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.billbear.common.response.PageData;
import ${basePackage}.${moduleName}.entity.${table.className};
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import ${basePackage}.${moduleName}.stub.request.${table.className}SearchRequest;

import java.util.List;

/**
 * <p>${table.remarks} service</p>
 *
 * @since ${.now}
 * @author generator
*/
public interface ${table.className}Service extends IService<${table.className}> {


    /**
     * 新增
     *
     * @param request
     * @return
    */
    Boolean add(${table.className}Request request);


    /**
     * 修改
     *
     * @param request
     * @return
    */
    Boolean upd(${table.className}Request request);

    /**
     * 删除
     *
     * @param id
     * @return
    */
    Boolean del(String id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
    */
    Boolean delByIds(List<String> ids);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
    */
    ${table.className}DTO getOneById(String id);

    /**
     * 分页查询
     *
     * @param request
     * @return
    */
    PageData<${table.className}DTO> search(${table.className}SearchRequest request);

    /**
     * 查询列表
     *
     * @param request
     * @return
    */
    List<${table.className}DTO> getList(${table.className}SearchRequest request);


    /**
     * 查询列表
     *
     * @param ids
     * @return
    */
    List<${table.className}DTO> getListByIds(List<String> ids);
}