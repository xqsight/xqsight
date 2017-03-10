/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.common.freemarker.TemplateEngineException;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsArticleService;

/**
 * <p>文章表 controller</p>
 * <p>Table: cms_article - 文章表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:03
 */
@RestController
@RequestMapping("/cms/article/")
public class CmsArticleController {

    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping("save")
    public Object save(CmsArticle cmsArticle) {
        try {
            cmsArticleService.saveArticle(cmsArticle);
        } catch (TemplateEngineException e) {
            e.printStackTrace();
            return MessageSupport.successMsg("保存成功,生成模板出错");
        }
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(CmsArticle cmsArticle) {
        try {
            cmsArticleService.updateArticle(cmsArticle);
        } catch (TemplateEngineException e) {
            e.printStackTrace();
            return MessageSupport.successMsg("修改成功,生成模板出错");
        }
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long articleId) {
        cmsArticleService.delete(articleId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long articleId) {
        cmsArticleService.logicDel(articleId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(XqsightPage xqsightPage) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<CmsArticle> cmsArticles = cmsArticleService.search(null);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, cmsArticles);
    }

    @RequestMapping("querybyid")
    public Object queryById(Long articleId) {
        CmsArticle cmsArticle = cmsArticleService.get(articleId);
        cmsArticle.setTags(cmsArticleService.queryTagByArticle(articleId));
        return MessageSupport.successDataMsg(cmsArticle, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<CmsArticle> cmsArticles = cmsArticleService.search(null);
        return MessageSupport.successDataMsg(cmsArticles, "查询成功");
    }

}