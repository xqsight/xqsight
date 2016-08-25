/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.util.HtmlUtil;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.mysqlmapper.CmsArticleMapper;
import com.xqsight.cms.mysqlmapper.CmsArticleViewMapper;
import com.xqsight.cms.mysqlmapper.CmsCommentMapper;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.upload.service.FileUploadFTPService;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>接口实现类service</p>
 * <p>Table: CMS_ARTICLE - 文章表</p>
 * @since 2016-05-07 08:02:43
 */
@Service
public class CmsArticleServiceImpl implements CmsArticleService {

	private static Logger logger = LogManager.getLogger(CmsArticleServiceImpl.class);

	@Autowired
	private CmsArticleMapper cmsArticleMapper;

	@Autowired
	private CmsArticleViewMapper cmsArticleViewMapper;

	@Autowired
	private CmsCommentMapper cmsCommentMapper;

	@Autowired
	private FileUploadFTPService fileUploadFTPService;


	@Override
	public void saveCmsArticle(CmsArticle cmsArticle,String img){
		logger.debug("出入参数:{}", JSON.toJSONString(cmsArticle));
		List<String> delurl = new ArrayList<String>();
		if(StringUtils.isNotBlank(img)) {
			List<String> imgList = HtmlUtil.pickImg(cmsArticle.getArticleContent());
			String[] imgs = new String[imgList.size()];
			for(int i =0 ;i < imgList.size() ;i++){
				imgs[i] = imgList.get(i).toString();
			}
			for (String imgUrl : img.split(",")) {
				if (!ArrayUtils.contains(imgs, imgUrl)) {
					delurl.add(imgUrl.split("=")[1].toString());
				}
			}
		}
		if(delurl.size()>0)
		{
			try {
				fileUploadFTPService.delFTP(delurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cmsArticleMapper.saveCmsArticle(cmsArticle);
	}


	@Override
	public void updateCmsArticle(CmsArticle cmsArticle,String img) {
		logger.debug("出入参数:{}", JSON.toJSONString(cmsArticle));
		List<String> delurl = new ArrayList<String>();

		if(StringUtils.isNotBlank(img)) {
			List<String> imgList = HtmlUtil.pickImg(cmsArticle.getArticleContent());
			String[] imgs = new String[imgList.size()];
			for(int i =0 ;i < imgList.size() ;i++){
				imgs[i] = imgList.get(i).toString();
			}
			for (String imgUrl : img.split(",")) {
				if (!ArrayUtils.contains(imgs, imgUrl)) {
					delurl.add(imgUrl.split("=")[1].toString());
				}
			}
		}
		if(delurl.size()>0)
		{
			try {
				fileUploadFTPService.delFTP(delurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cmsArticleMapper.updateCmsArticle(cmsArticle);
	}


	@Override
	public void deleteCmsArticle(Long articleId) {
		logger.debug("出入参数:articleId={}", articleId);
		//2016-05-24  删除文章时删除ftp图片
		CmsArticle cmsArticle = cmsArticleMapper.queryCmsArticleById(articleId);
		//提取文档中的img
		Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
		Matcher m = p.matcher(cmsArticle.getArticleContent());
		List<String> delurl = new ArrayList<String>();
		while(m.find()) {
			delurl.add(m.group(1).split("=")[1].toString());
			System.out.println(m.group(1));
		}
		if(delurl.size()>0)
		{
			try {
				fileUploadFTPService.delFTP(delurl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cmsArticleMapper.deleteCmsArticle(articleId);
		cmsCommentMapper.deleteCmsCommentByAssocicationId(articleId);
	}


	@Override
	public List<CmsArticle> queryCmsArticle() {
		return cmsArticleMapper.queryCmsArticle();
	}


	@Override
	public CmsArticle queryCmsArticleById(Long articleId ){
		logger.debug("传入参数:articleId={}",articleId);
		return cmsArticleMapper.queryCmsArticleById(articleId);
	}

	@Override
	public List<CmsArticle> queryCmsArticleByModelId(Long modelId) {
		logger.debug("传入参数:modelId={}",modelId);
		return cmsArticleMapper.queryCmsArticleByModelId(modelId);
	}

	@Override
	public List<CmsArticle> queryCmsArticleByModelIds(String... modelId) {
		logger.debug("传入参数:modelId={}",JSON.toJSONString(modelId));
		String modelIds = StringUtils.join(modelId,",");
		return cmsArticleMapper.queryCmsArticleByModelIds(modelIds);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewByModelId(int modelId) {
		logger.debug("传入参数:modelId={}",modelId);
		return cmsArticleViewMapper.queryCmsArticleViewByModelId(modelId);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewByModelIds(String... modelId) {
		logger.debug("传入参数:modelId={}",JSON.toJSONString(modelId));
		String modelIds = StringUtils.join(modelId,",");
		return cmsArticleViewMapper.queryCmsArticleViewByModelIds(modelIds);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewByModelIdAndActive(int modelId, int active) {
		logger.debug("传入参数:modelId={},active={}",modelId,active);
		return cmsArticleViewMapper.queryCmsArticleViewByModelIdAndActive(modelId,active);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprId(int modelId, String createOprId) {
		logger.debug("传入参数:modelId={},createOprId={}",modelId,createOprId);
		return cmsArticleViewMapper.queryCmsArticleViewByModelIdAndCreateOprId(modelId,createOprId);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprIdAndActive(int modelId, String createOprId, int active) {
		logger.debug("传入参数:modelId={},createOprId={}",modelId,createOprId);
		return cmsArticleViewMapper.queryCmsArticleViewByModelIdAndCreateOprIdAndActive(modelId,createOprId,active);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(Long modelId, String orderType) {
		logger.debug("传入参数:modelId={},orderType={}",modelId,orderType);
		return cmsArticleViewMapper.queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(modelId,orderType);
	}

	@Override
	public List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndUserId(Long modelId, String createOprId) {
		logger.debug("传入参数:modelId={},createOprId={}",modelId,createOprId);
		return cmsArticleViewMapper.queryCmsArticleViewWithFirstPicByModelIdAndUserId(modelId,createOprId);
	}

	@Override
	public Map<String, Object> queryCmsArticleViewByArticleId(Long articleId) {
		logger.debug("传入参数:articleId={}",articleId);
		return cmsArticleViewMapper.queryCmsArticleViewByArticleId(articleId);
	}

	@Override
	public void updateCmsArticleHitByArticleId(int articleHit, Long articleId) {
		logger.debug("传入参数:articleHit={},articleId={}",articleHit,articleId);
		cmsArticleMapper.updateCmsArticleHitByArticleId(articleHit,articleId);
	}

	@Override
	public void updateCmsArticleActiveByArticleId(int active, Long articleId) {
		logger.debug("传入参数:active={},articleId={}",active,articleId);
		cmsArticleMapper.updateCmsArticleActiveByArticleId(active,articleId);
	}

}