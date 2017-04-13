/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.mapper;


import com.xqsight.cms.model.CmsArticle;
import com.xqsight.common.base.dao.ICrudDao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>文章表实现类service</p>
 * <p>Table: cms_article - 文章表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:03
 */
public interface CmsArticleMapper extends ICrudDao<CmsArticle, Long> {

    @Insert("insert into cms_article_tag (tag_id,article_id)values(#{tagId, jdbcType=NUMERIC},#{articleId, jdbcType=NUMERIC})")
    void saveArticleTag(@Param("articleId") long articleId, @Param("tagId") long tagId);

    @Delete("delete from cms_article_tag where article_id =#{articleId, jdbcType=NUMERIC}")
    void deleteArticleTag(@Param("articleId") long articleId);

    @Select("select ct.tag_name from cms_tag ct left join cms_article_tag cat on ct.tag_id = cat.tag_id where cat.article_id = #{articleId, jdbcType=NUMERIC}")
    List<String> queryArticleTag(@Param("articleId") long articleId);
}