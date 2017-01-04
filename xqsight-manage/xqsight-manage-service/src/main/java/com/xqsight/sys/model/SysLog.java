/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.sys.model;

import com.xqsight.common.model.Model;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>系统日志实体类</p>
 * <p>Table: sys_log - --> SysLog</p>
 * <p>系统日志</p>
 * @since 2016-08-24 05:17:09
 */
public class SysLog extends Model implements Serializable {

    private static final long serialVersionUID = 7204277315878190079L;

	/** 主键 */
    private Long logId;

	/** logd_type - 日志类型 */
    private String logType;
	/** log_title - 日志标题 */
    private String logTitle;
	/** log_desc - 日志描述 */
    private String logDesc;
	/** req_ip - 请求ip */
    private String reqIp;
	/** req_url - 请求url */
    private String reqUrl;
	/** req_method - 请求方法 */
    private String reqMethod;
	/** req_data - 请求数据 */
    private String reqData;
	/** exceptions - 请求异常 */
    private String exception;
	/** agent_user - 用户代理 */
    private String agentUser;

    public static final String TYPE_ACCESS = "1";
    public static final String TYPE_EXCEPTION = "2";

    public Long getLogId(){
        return this.logId;
    }
    public void setLogId(Long logId){
        this.logId = logId;
    }
	public String getLogType(){
        return this.logType;
    }
    public void setLogType(String logdType){
        this.logType = logdType;
    }
	public String getLogTitle(){
        return this.logTitle;
    }
    public void setLogTitle(String logTitle){
        this.logTitle = logTitle;
    }
	public String getLogDesc(){
        return this.logDesc;
    }
    public void setLogDesc(String logDesc){
        this.logDesc = logDesc;
    }
	public String getReqIp(){
        return this.reqIp;
    }
    public void setReqIp(String reqIp){
        this.reqIp = reqIp;
    }
	public String getReqUrl(){
        return this.reqUrl;
    }
    public void setReqUrl(String reqUrl){
        this.reqUrl = reqUrl;
    }
	public String getReqMethod(){
        return this.reqMethod;
    }
    public void setReqMethod(String reqMethod){
        this.reqMethod = reqMethod;
    }
	public String getReqData(){
        return this.reqData;
    }
    public void setReqData(String reqData){
        this.reqData = reqData;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
	public String getAgentUser(){
        return this.agentUser;
    }
    public void setAgentUser(String agentUser){
        this.agentUser = agentUser;
    }
    /**
     * 设置请求参数
     * @param paramMap
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setReqData(Map paramMap){
        if (paramMap == null){
            return;
        }
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String[]> param : ((Map<String, String[]>)paramMap).entrySet()){
            params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
            String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
            params.append(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue);
        }
        this.reqData = params.toString();
    }

    @Override
    public Serializable getPK() {
        return this.logId;
    }
}