/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xiaoleilu.hutool.util.StrUtil;
import com.xqsight.chronic.model.Product;
import com.xqsight.chronic.service.ProductService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.utils.MapKeyHandle;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * <p>产品表 controller</p>
 * <p>Table: PRODUCT - 产品表</p>
 * @since 2016-05-13 03:29:34
 */
@RestController
@RequestMapping("/product/")
public class ProductController{

	@Autowired
	private ProductService productService;

	@RequestMapping("save")
	public Object saveProduct(Product product) {
		product.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		/** 描述为空，获取前20个字符 **/
		if(StringUtils.isBlank(product.getProductDescription()))
			product.setProductDescription(StrUtil.getChinese(product.getProductContent(),20));

		productService.saveProduct(product);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateProduct(Product product) {
		product.setUpdOprId(SSOUtils.getCurrentUserId().toString());
		/** 描述为空，获取前20个字符 **/
		if(StringUtils.isBlank(product.getProductDescription()))
			product.setProductDescription(StrUtil.getChinese(product.getProductContent(),20));

		productService.updateProduct(product);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteProduct(Long productId) {
		productService.deleteProduct(productId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryProduct(XqsightPage xqsightPage,String productName) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Map<String,Object>> products = productService.queryProductWithFirstPic(productName);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(products));
	}

	@RequestMapping("querybyid")
	public Object queryProduct(Long productId) {
		Product product = productService.queryProductById(productId);
		return MessageSupport.successDataMsg(product,"查询成功");
	}
}