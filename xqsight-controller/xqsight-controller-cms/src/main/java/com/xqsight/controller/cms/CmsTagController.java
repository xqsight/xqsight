/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.service.CmsTagService;

/**
 * <p>标签表 controller</p>
 * <p>Table: cms_tag - 标签表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:38
 */
@RestController
@RequestMapping("/cms/tag/")
public class CmsTagController {

    @Autowired
    private CmsTagService cmsTagService;

    @RequestMapping("save")
    public Object save(CmsTag cmsTag) {
        cmsTagService.save(cmsTag, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(CmsTag cmsTag) {
        cmsTagService.update(cmsTag, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long tagId) {
        cmsTagService.delete(tagId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long tagId) {
        cmsTagService.logicDel(tagId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(XqsightPage xqsightPage) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<CmsTag> cmsTags = cmsTagService.search(null);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, cmsTags);
    }

    @RequestMapping("querybyid")
    public Object queryById(Long tagId) {
        CmsTag cmsTag = cmsTagService.get(tagId);
        return MessageSupport.successDataMsg(cmsTag, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall(String tagName) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("tag_name", tagName).end();
        List<CmsTag> cmsTags = cmsTagService.search(propertyFilters);
        return MessageSupport.successDataMsg(cmsTags, "查询成功");
    }

}