/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.math.BigDecimal;

import com.xqsight.common.model.BaseModel;

/**
 * <p>产品表实体类</p>
 * <p>Table: PRODUCT - --> Product</p>
 * <p>产品表</p>
 * @since 2016-05-13 03:29:34
 */
public class Product extends BaseModel {

	/** 主键 */
    private Long productId;

	/** PRODUCT_NAME - 产品名称 */
    private String productName;
	/** PRODUCT_DESCRIPTION - 产品描述 */
    private String productDescription;
	/** PRODUCT_CONTENT - 产品内容 */
    private String productContent;
	/** PRODUCT_TYPE - 产品类型 */
    private String productType;
	/** PRODUCT_PRICE - 产品价格 */
    private BigDecimal productPrice;
	/** BEGIN_TIME - 开始时间 */
    private String beginTime;
	/** END_TIME - 结束时间 */
    private String endTime;
	/** FILE_ID - 附件ID */
    private String fileId;

    public Long getProductId(){
        return this.productId;
    }
    public void setProductId(Long productId){
        this.productId = productId;
    }
	public String getProductName(){
        return this.productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
	public String getProductDescription(){
        return this.productDescription;
    }
    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }
	public String getProductContent(){
        return this.productContent;
    }
    public void setProductContent(String productContent){
        this.productContent = productContent;
    }
	public String getProductType(){
        return this.productType;
    }
    public void setProductType(String productType){
        this.productType = productType;
    }
	public BigDecimal getProductPrice(){
        return this.productPrice;
    }
    public void setProductPrice(BigDecimal productPrice){
        this.productPrice = productPrice;
    }
	public String getBeginTime(){
        return this.beginTime;
    }
    public void setBeginTime(String beginTime){
        this.beginTime = beginTime;
    }
	public String getEndTime(){
        return this.endTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
	public String getFileId(){
        return this.fileId;
    }
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
}