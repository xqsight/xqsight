/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.xqsight.chronic.model.Product;


/**
 * <p>产品表数据库Mapper类</p>
 * <p>产品表</p>
 * @since 2016-05-13 03:29:34
 */
public interface ProductMapper {

	@Insert(" INSERT INTO PRODUCT(PRODUCT_NAME,PRODUCT_DESCRIPTION,PRODUCT_CONTENT,PRODUCT_TYPE,PRODUCT_PRICE,BEGIN_TIME,END_TIME,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{productName,jdbcType=VARCHAR},#{productDescription,jdbcType=VARCHAR},#{productContent,jdbcType=VARCHAR},#{productType,jdbcType=VARCHAR},#{productPrice,jdbcType=NUMERIC},#{beginTime,jdbcType=TIMESTAMP},#{endTime,jdbcType=TIMESTAMP},#{fileId,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "productId")
	void saveProduct(Product product);
	
	@Update(" UPDATE PRODUCT SET PRODUCT_NAME=#{productName,jdbcType=VARCHAR},PRODUCT_DESCRIPTION=#{productDescription,jdbcType=VARCHAR},PRODUCT_CONTENT=#{productContent,jdbcType=VARCHAR},PRODUCT_TYPE=#{productType,jdbcType=VARCHAR},PRODUCT_PRICE=#{productPrice,jdbcType=NUMERIC},FILE_ID=#{fileId,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE PRODUCT_ID=#{productId,jdbcType=NUMERIC}")
	void updateProduct(Product product);
	
	@Delete(" DELETE FROM PRODUCT WHERE PRODUCT_ID=#{productId,jdbcType=NUMERIC}")
	void deleteProduct(@Param("productId") Long productId);

	@Select(" SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,PRODUCT_CONTENT,PRODUCT_TYPE,PRODUCT_PRICE,BEGIN_TIME,END_TIME,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM PRODUCT ORDER BY CREATE_TIME DESC")
	List<Product> queryProduct();
	
	@Select("SELECT P.PRODUCT_ID,P.PRODUCT_NAME,P.PRODUCT_DESCRIPTION,P.PRODUCT_PRICE,ifnull(SF.FILE_URL,' ') IMG_PATH FROM PRODUCT P LEFT JOIN SYS_FILE SF ON SF.FILE_ID = SUBSTRING_INDEX(P.FILE_ID,',',1) WHERE P.PRODUCT_NAME LIKE '%${productName}%' ORDER BY P.CREATE_TIME DESC")
	List<Map<String,Object>> queryProductWithFirstPic(@Param("productName")String productName);

	@Select(" SELECT PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,PRODUCT_CONTENT,PRODUCT_TYPE,PRODUCT_PRICE,BEGIN_TIME,END_TIME,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM PRODUCT WHERE PRODUCT_ID=#{productId,jdbcType=NUMERIC}")
	Product queryProductById(@Param("productId") Long productId);
}