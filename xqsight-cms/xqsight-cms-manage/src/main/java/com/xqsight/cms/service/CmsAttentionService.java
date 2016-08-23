/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import java.util.List;
import java.util.Map;

import com.xqsight.cms.model.CmsAttention;
import org.apache.ibatis.annotations.Param;

/**
 * <p>接口类service</p>
 * <p>Table: CMS_ATTENTION - 用户收藏表</p>
 *
 * @since 2016-05-07 07:49:38
 */
public interface CmsAttentionService {

    /**
     * 保存收藏  置顶
     *
     * @param @param cmsAttention    设定文件
     * @return void    返回类型
     * @throws
     * @Title: saveCmsAttention
     * @Description: TODO
     */
    void saveCmsAttention(CmsAttention cmsAttention);

    /**
     * 修改 收藏 置顶
     *
     * @param @param cmsAttention    设定文件
     * @return void    返回类型
     * @throws
     * @Title: updateCmsAttention
     * @Description: TODO
     */
    void updateCmsAttention(CmsAttention cmsAttention);

    /**
     * 删除收藏 置顶 根据 关联键 关注类型  关注着
     *
     * @param associcationId
     * @param attentionType
     * @param createOprId
     */
    void deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(Long associcationId, int attentionType, String createOprId);

    /**
     * 删除收藏 置顶 根据 管理键 和 关注类型
     *
     * @param associcationId
     * @param attentionType
     */
    void deleteCmsAttentionByAssocicationIdAndAttentionType(Long associcationId, int attentionType);

    /**
     * 查询 根据关注类型
     *
     * @param attentionType
     * @return
     */
    List<CmsAttention> queryCmsAttentionByAttentionType(int attentionType);

    /**
     * 查询 根据主键
     *
     * @param attentionId
     * @return
     */
    CmsAttention queryCmsAttentionByAttentionId(Long attentionId);

    /**
     * 查询 当前 帖子当前用户是否关注
     * @param associcationId
     * @param attentionType
     * @param createOprId
     * @return
     */
    CmsAttention queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(Long associcationId, int attentionType, String createOprId);

    /**
     * 查询我的收藏  收藏对象为帖子
     *
     * @param createOprId
     * @return
     */
    List<Map<String, Object>> queryCmsAttentionToStoreByUser(String createOprId);

    /**
     * 查询我的关注 关注对象为人
     *
     * @param createOprId
     * @return
     */
    List<Map<String, Object>> queryCmsAttentionToAttenionByUser(String createOprId);
}