/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.model.Product;
import com.xqsight.chronic.mysqlmapper.ProductMapper;
import com.xqsight.chronic.service.ProductService;


/**
 * <p>产品表接口实现类service</p>
 * <p>Table: PRODUCT - 产品表</p>
 * @since 2016-05-13 03:29:34
 */
 @Service
public class ProductServiceImpl implements ProductService {

	private static Logger logger = LogManager.getLogger(ProductServiceImpl.class); 
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public void saveProduct(Product product){
		logger.debug("出入参数:{}", JSON.toJSONString(product));
		productMapper.saveProduct(product);
	}
	
	@Override
	public void updateProduct(Product product) {
		logger.debug("出入参数:{}", JSON.toJSONString(product));
		productMapper.updateProduct(product);
	}
	
	@Override
	public void deleteProduct(Long productId) {
		logger.debug("出入参数:{}", productId);
		productMapper.deleteProduct(productId);
	}

	@Override
	public Product queryProductById(Long productId ){
		return productMapper.queryProductById(productId);
	}

	@Override
	public List<Product> queryProduct() {
		return productMapper.queryProduct();
	}

	@Override
	public List<Map<String,Object>> queryProductWithFirstPic() {
		return productMapper.queryProductWithFirstPic();
	}
}