package com.xqsight.cms.model.vo;

import com.xqsight.cms.model.CmsArticleReport;

/**
 * Created by wangganggang on 16/9/9.
 */
public class CmsArticleReportVo extends CmsArticleReport {

    /** 标题 **/
    private String articleTitle;
    private String articleId;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
