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
import com.xqsight.common.model.constants.ErrorMessageConstants;
import com.xqsight.system.model.SysOffice;
import com.xqsight.system.service.SysOfficeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>机构表 controller</p>
 * <p>Table: sys_office - 机构表</p>
 * @since 2017-04-05 05:21:31
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/office")
public class SysOfficeController extends BaseTreeController<SysOfficeService,SysOffice,Long> {
    @Override
    protected void preDelete(SysOffice sysOffice) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("parent_id", "" + sysOffice.getId()).end();
        List<SysOffice> sysOffices = service.getByFilters(propertyFilters);
        if (sysOffices != null && sysOffices.size() > 0) {
            throw new GlobalException(ErrorMessageConstants.ERROR_10000, "该项还有子项不可删除");
        }
    }
}