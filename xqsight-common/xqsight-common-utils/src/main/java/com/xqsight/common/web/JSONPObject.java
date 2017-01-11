/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.common.web;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 返回JSONP的对象，当需要返回JSONP时，可以使用该对象
 * @author xqsight-jerry
 * @version JSONPObject.java, v 0.1 2015年7月30日 上午8:20:03 xqsight-jerry
 */
public class JSONPObject {
    
    private String callBackFunction;
    
    private Object jsonObject;
    
    private SerializerFeature[] feature;

    /**
     * Getter method for property <tt>callBackFunction</tt>.
     * 
     * @return property value of callBackFunction
     */
    public String getCallBackFunction() {
        return callBackFunction;
    }

    /**
     * Setter method for property <tt>callBackFunction</tt>.
     * 
     * @param callBackFunction value to be assigned to property callBackFunction
     */
    public void setCallBackFunction(String callBackFunction) {
        this.callBackFunction = callBackFunction;
    }

    /**
     * Getter method for property <tt>jsonObject</tt>.
     * 
     * @return property value of jsonObject
     */
    public Object getJsonObject() {
        return jsonObject;
    }

    /**
     * Setter method for property <tt>jsonObject</tt>.
     * 
     * @param jsonObject value to be assigned to property jsonObject
     */
    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * Getter method for property <tt>feature</tt>.
     * 
     * @return property value of feature
     */
    public SerializerFeature[] getFeature() {
        return feature;
    }

    /**
     * Setter method for property <tt>feature</tt>.
     * 
     * @param featue value to be assigned to property feature
     */
    public void setFeatue(SerializerFeature[] feature) {
        this.feature = feature;
    }

}
