/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.xqsight.cms.model.CmsPosition;
import com.xqsight.cms.service.CmsPositionService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>职位表 controller</p>
 * <p>Table: cms_position - 职位表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:22
 */
@RestController
@RequestMapping("/cms/position/")
public class CmsPositionController {

    @Autowired
    private CmsPositionService cmsPositionService;

    @RequestMapping("save")
    public Object save(CmsPosition cmsPosition) {
        if (StringUtils.equals(cmsPosition.getParentId(), "0")) {
            cmsPosition.setParentIds(",0,");
        } else {
            CmsPosition parentPosition = cmsPositionService.get(Long.valueOf(cmsPosition.getParentId()));
            cmsPosition.setParentIds(parentPosition.getParentIds() + parentPosition.getPositionId() + Constants.COMMA_SIGN_SPLIT_NAME);
        }
        cmsPositionService.save(cmsPosition, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(CmsPosition cmsPosition) {
        cmsPositionService.update(cmsPosition, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long positionId) {
        cmsPositionService.delete(positionId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long positionId) {
        cmsPositionService.logicDel(positionId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(String parentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("parent_id", parentId).end();
        List<CmsPosition> cmsPositions = cmsPositionService.search(propertyFilters);
        return MessageSupport.successDataMsg(cmsPositions, "查询成功");
    }

    @RequestMapping("querybyid")
    public Object queryById(Long positionId) {
        CmsPosition cmsPosition = cmsPositionService.get(positionId);
        return MessageSupport.successDataMsg(cmsPosition, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<CmsPosition> cmsPositions = cmsPositionService.search(null);
        return MessageSupport.successDataMsg(cmsPositions, "查询成功");
    }

    @RequestMapping("querytree")
    public Object queryTree() {
        List<CmsPosition> cmsPositions = cmsPositionService.search(null);
        cmsPositions = new TreeSupport<CmsPosition>().generateTree(cmsPositions);
        return MessageSupport.successDataMsg(cmsPositions, "查询成功");
    }
}