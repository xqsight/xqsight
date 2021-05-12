<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.feign.client;

import com.billbear.common.response.PageData;
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import ${basePackage}.${moduleName}.stub.request.${table.className}SearchRequest;

import java.util.List;

/**
 * <p>${table.remarks} client</p>
 *
 * @since ${.now}
 * @author generator
*/
public interface ${table.className}Client {


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
    ${table.className}DTO getById(String id);

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
    List<${table.className}DTO> list(${table.className}SearchRequest request);

    /**
     * 查询列表
     *
     * @param ids
     * @return
    */
    List<${table.className}DTO> listByIds(List<String> ids);

}