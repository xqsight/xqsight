/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.base.controller.BaseController;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.system.model.SysArea;
import com.xqsight.system.service.SysAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>区域表 controller</p>
 * <p>Table: sys_area - 区域表</p>
 *
 * @author wangganggang
 * @since 2017-04-05 05:21:08
 */
@RestController
@RequestMapping("/sys/area")
public class SysAreaController extends BaseController<SysAreaService, SysArea, Long> {

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Object save(SysArea sysArea) {
        if (StringUtils.equals(sysArea.getParentId(), "0")) {
            sysArea.setParentIds(",0,");
        } else {
            SysArea parentArea = service.getById(Long.valueOf(sysArea.getParentId()));
            sysArea.setParentIds(parentArea.getParentIds() + parentArea.getAreaId() + Constants.COMMA_SIGN_SPLIT_NAME);
        }
        int iRet = service.add(sysArea);
        return new BaseResult(iRet);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public Object queryTree() {
        List<SysArea> sysAreas = service.getAll();
        sysAreas = new TreeSupport<SysArea>().generateTree(sysAreas);
        return new BaseResult(sysAreas);
    }
}