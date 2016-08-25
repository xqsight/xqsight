/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import com.xqsight.common.model.BaseModel;

/**
 * <p>基因大讲堂表实体类</p>
 * <p>Table: GENE_VEDIO - --> GeneVedio</p>
 * <p>基因大讲堂表</p>
 * @since 2016-05-13 03:29:55
 */
public class GeneVedio extends BaseModel {

	/** 主键 */
    private Long vedioId;

	/** PRODUCT_NAME - 视频名称 */
    private String vedioName;
	/** PRODUCT_DESCRIPTION - 视频描述 */
    private String vedioDescription;
	/** PRODUCT_TYPE - 视频类型 */
    private String vedioType;
	/** FILE_ID - 附件ID */
    private String fileId;

    public Long getVedioId(){
        return this.vedioId;
    }
    public void setVedioId(Long vedioId){
        this.vedioId = vedioId;
    }
	public String getVedioName(){
        return this.vedioName;
    }
    public void setVedioName(String vedioName){
        this.vedioName = vedioName;
    }
	public String getVedioDescription(){
        return this.vedioDescription;
    }
    public void setVedioDescription(String vedioDescription){
        this.vedioDescription = vedioDescription;
    }
	public String getVedioType(){
        return this.vedioType;
    }
    public void setVedioType(String vedioType){
        this.vedioType = vedioType;
    }
	public String getFileId(){
        return this.fileId;
    }
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
}