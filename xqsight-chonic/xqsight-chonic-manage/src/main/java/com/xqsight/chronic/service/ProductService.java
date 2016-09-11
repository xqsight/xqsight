/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import java.util.List;
import java.util.Map;

import com.xqsight.chronic.model.Product;

/**
 * <p>产品表接口类service</p>
 * <p>Table: PRODUCT - 产品表</p>
 * @since 2016-05-13 03:29:34
 */
public interface ProductService {

	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProduct(Long productId);

	Product queryProductById(Long productId);

	List<Map<String,Object>> queryProductWithFirstPic(String productName);

	List<Product> queryProduct();
}