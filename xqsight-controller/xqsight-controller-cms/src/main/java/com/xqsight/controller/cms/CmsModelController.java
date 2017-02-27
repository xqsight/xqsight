/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.support.MessageSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>模块表 controller</p>
 * <p>Table: cms_model - 模块表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:18
 */
@RestController
@RequestMapping("/cms/model/")
public class CmsModelController {

    @Autowired
    private CmsModelService cmsModelService;

    @RequestMapping("save")
    public Object save(CmsModel cmsModel) {
        cmsModelService.save(cmsModel, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(CmsModel cmsModel) {
        cmsModelService.update(cmsModel, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long modelId) {
        cmsModelService.delete(modelId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long modelId) {
        cmsModelService.logicDel(modelId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(String siteId, String modelName) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("site_id", siteId).matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("model_name", StringUtils.trimToEmpty(modelName)).end();
        List<CmsModel> cmsModels = cmsModelService.search(propertyFilters);
        return MessageSupport.successDataMsg(cmsModels, "查询成功");
    }

    @RequestMapping("querybyid")
    public Object queryById(Long modelId) {
        CmsModel cmsModel = cmsModelService.get(modelId);
        return MessageSupport.successDataMsg(cmsModel, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<CmsModel> cmsModels = cmsModelService.search(null);
        return MessageSupport.successDataMsg(cmsModels, "查询成功");
    }

}