<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.billbear.common.constants.CommonConstants;
import com.billbear.common.response.PageData;
import ${basePackage}.${moduleName}.entity.${table.className};
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.service.${table.className}Service
import ${basePackage}.${moduleName}.mapper.${table.className}Mapper;
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import ${basePackage}.${moduleName}.stub.request.${table.className}SearchRequest;
import ${basePackage}.${moduleName}.service.convert.${table.className}ConvertMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
* <p>${table.remarks} service impl</p>
*
* @since ${.now}
* @author generator
*/
@Slf4j
@Service
public class ${table.className}Service extends ServiceImpl<${table.className}Mapper, ${table.className}> implements ${table.className}Service {


    @Override
    public Boolean add(${table.className}Request request){
        ${table.className} ${table.javaProperty} = ${table.className}ConvertMapper.INSTANCE.${table.javaProperty}RequestToEntity(request);
        return this.save(${table.javaProperty});
    }

    @Override
    public Boolean upd(${table.className}Request request){
        ${table.className} ${table.javaProperty} = ${table.className}ConvertMapper.INSTANCE.${table.javaProperty}RequestToEntity(request);
        return this.updateById(${table.javaProperty});
    }

    @Override
    public Boolean del(String id){
        ${table.className} ${table.javaProperty} = new ${table.className}();
        ${table.javaProperty}.setId(id);
        ${table.javaProperty}.setStatus(CommonConstants.STATUS_DELETED);
        return this.updateById(${table.javaProperty});
    }

    @Override
    public Boolean delByIds(List<String> ids){
        if (!CollectionUtils.isEmpty(ids)) {
            List<${table.className}> ${table.javaProperty}s =  ids.stream().map(id->{
                ${table.className} ${table.javaProperty} = new ${table.className}();
                ${table.javaProperty}.setId(id);
                ${table.javaProperty}.setStatus(CommonConstants.STATUS_DELETED);
                return ${table.javaProperty};
                }).collect(Collectors.toList());
            return this.updateBatchById(${table.javaProperty}s);
        }
        return Boolean.FALSE;
    }

    @Override
    public ${table.className}DTO getOneById(String id){
        ${table.className} ${table.javaProperty} = this.getById(id);
        return ${table.className}ConvertMapper.INSTANCE.entityToDTO(${table.javaProperty});
    }

    @Override
    public PageData<${table.className}DTO> search(${table.className}SearchRequest request){
        LambdaQueryWrapper< ${table.className}> queryWrapper = new LambdaQueryWrapper<>();
        IPage< ${table.className}> page = this.page(request.getPage(),queryWrapper);
        return PageData.conversion(page, (d) -> ${table.className}ConvertMapper.entityToDTO((VerificationRecordExt) d));
    }

    @Override
    public List<${table.className}DTO> getList(${table.className}SearchRequest request){
            LambdaQueryWrapper< ${table.className}> queryWrapper = new LambdaQueryWrapper<>();
            IPage< ${table.className}> page = this.page(request.getPage(),queryWrapper);
            return PageData.conversion(page, (d) -> ${table.className}ConvertMapper.entityToDTO((VerificationRecordExt) d));

    }

    @Override
    public List<${table.className}DTO> getListByIds(List<String> ids){
        List< ${table.className}> ${table.javaProperty}s = this.listByIds(ids);
        return Optional.ofNullable(${table.javaProperty}s).orElseGet(Collections::emptyList).stream()
                    .map(${table.className}ConvertMapper::entityToDTO)
                    .collect(Collectors.toList());
    }
}