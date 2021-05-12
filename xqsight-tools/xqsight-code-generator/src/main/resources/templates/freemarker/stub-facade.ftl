<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.stub.facade;

import com.billbear.common.response.PageData;
import com.billbear.common.response.ResponseData;
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.stub.request.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>${table.remarks} facade</p>
 *
 * @since ${.now}
 * @author generator
*/
@FeignClient(name = "${r"${"}billbear.feign.${moduleName}:${moduleName}-service}")
@Tag(name = "${table.className}", description = "${table.remarks}")
@RequestMapping("/admin/${moduleName}/${table.controllerPath}")
public interface ${table.className}Feign {

     @PostMapping(value = "/add")
     @Operation(summary = "新增")
     ResponseData<Boolean> add(@RequestBody ${table.className}Request request);

     @PutMapping(value = "/upd")
     @Operation(summary = "修改")
     ResponseData<Boolean> upd(@RequestBody ${table.className}Request request);

     @PostMapping(value = "/del/{id}")
     @Operation(summary = "删除}")
     ResponseData<Boolean> del(@PathVariable("id") String id);

     @PostMapping(value = "/delByIds")
     @Operation(summary = "批量删除")
     ResponseData<Boolean> delByIds(@RequestBody List<String> ids);

     @GetMapping("/{id}")
     @Operation(summary = "根据id查询详情")
     ResponseData<${table.className}DTO> getById(@PathVariable("id") String id);

     @PostMapping(value = "/search")
     @Operation(summary = "分页查询")
     ResponseData<PageData<${table.className}DTO>> search(@RequestBody ${table.className}SearchRequest request);

     @PostMapping(value = "/list")
     @Operation(summary = "查询列表")
     ResponseData<List<${table.className}DTO>> list(@RequestBody ${table.className}SearchRequest request);

     @PostMapping(value = "/listByIds")
     @Operation(summary = "查询列表")
     ResponseData<List<${table.className}DTO>> listByIds(@RequestBody List<String> ids);
}