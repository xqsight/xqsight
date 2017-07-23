/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.base.controller.BaseTreeController;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.exception.GlobalException;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.system.model.SysDict;
import com.xqsight.system.service.SysDictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>系统字典表 controller</p>
 * <p>Table: sys_dict - 系统字典表</p>
 * @since 2017-04-05 05:21:13
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseTreeController<SysDictService,SysDict,Long> {

    @Override
    protected void prePut(SysDict sysDict) {
        if(sysDict.getPK() != null) {
            SysDict subDict = service.getById(sysDict.getDictId());
            if (subDict.getEditable() != null && subDict.getEditable() == Constants.DISABLE) {
                throw new GlobalException(ErrorMessageConstants.ERROR_10000, "该条数据是系统内置数据不可修改");
            }
        }
    }

    @Override
    protected void preDelete(SysDict sysDict) {
        if (sysDict.getEditable() != null && sysDict.getEditable() == Constants.DISABLE) {
            throw new GlobalException(ErrorMessageConstants.ERROR_10000, "该条数据是系统内置数据不可删除");
        }
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("parent_id", "" + sysDict.getId()).end();
        List<SysDict> sysDicts = service.getByFilters(propertyFilters);
        if (sysDicts != null && sysDicts.size() > 0) {
            throw new GlobalException(ErrorMessageConstants.ERROR_10000, "该项还有子项不可删除");
        }
    }
}