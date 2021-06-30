<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.billbear.common.constants.CommonConstants;
import com.billbear.common.response.PageData;
import ${basePackage}.${moduleName}.entity.${table.className};
import ${basePackage}.${moduleName}.stub.bean.${table.className}DTO;
import ${basePackage}.${moduleName}.service.${table.className}Service;
import ${basePackage}.${moduleName}.mapper.${table.className}Mapper;
import ${basePackage}.${moduleName}.stub.request.${table.className}Request;
import ${basePackage}.${moduleName}.stub.request.${table.className}SearchRequest;
import ${basePackage}.${moduleName}.service.convert.${table.className}ConvertMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>${table.remarks} service impl</p>
 *
 * @since ${.now}
 * @author generator
*/
@Log4j2
@Service
public class ${table.className}ServiceImpl extends ServiceImpl<${table.className}Mapper, ${table.className}> implements ${table.className}Service {


    @Override
    public Boolean add(${table.className}Request request){
        ${table.className} ${table.javaProperty} = ${table.className}ConvertMapper.INSTANCE.${table.javaProperty}RequestToEntity(request);
        return this.save(${table.javaProperty});
    }

    @Override
    public Boolean upd(${table.className}Request request){
        return this.updateById(${table.className}ConvertMapper.INSTANCE.${table.javaProperty}RequestToEntity(request));
    }

    @Override
    public Boolean del(String id){
        return this.lambdaUpdate().eq(${table.className}::getId,id)
            .set(${table.className}::getStatus,CommonConstants.STATUS_DELETED).update();
    }

    @Override
    public Boolean delByIds(List<String> ids){
        if (!CollectionUtils.isEmpty(ids)) {
            return this.lambdaUpdate().in(${table.className}::getId,ids)
                .set(${table.className}::getStatus,CommonConstants.STATUS_DELETED).update();
        }
        return Boolean.FALSE;
    }

    @Override
    public ${table.className}DTO getOneById(String id){
        return ${table.className}ConvertMapper.INSTANCE.entityToDTO(this.getById(id));
    }

    @Override
    public PageData<${table.className}DTO> search(${table.className}SearchRequest request){
        LambdaQueryWrapper< ${table.className}> queryWrapper = new LambdaQueryWrapper<>();
        IPage< ${table.className}> page = this.page(request.getPage(),queryWrapper);
        return PageData.conversion(page, (d) -> ${table.className}ConvertMapper.INSTANCE.entityToDTO((${table.className}) d));
    }

    @Override
    public List<${table.className}DTO> getList(${table.className}SearchRequest request){
        LambdaQueryWrapper< ${table.className}> queryWrapper = new LambdaQueryWrapper<>();
        List<${table.className}> list = this.list(queryWrapper);
        return Optional.ofNullable(list).orElseGet(Collections::emptyList).stream()
        .map(${table.className}ConvertMapper.INSTANCE::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public List<${table.className}DTO> getListByIds(List<String> ids){
        return Optional.ofNullable(this.listByIds(ids)).orElseGet(Collections::emptyList).stream()
                .map(${table.className}ConvertMapper.INSTANCE::entityToDTO).collect(Collectors.toList());
    }
}