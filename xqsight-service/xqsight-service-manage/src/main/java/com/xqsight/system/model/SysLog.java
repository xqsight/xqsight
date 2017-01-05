/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>系统日志实体类</p>
 * <p>Table: sys_log - --> SysLog</p>
 * <p>系统日志</p>
 * @since 2017-01-05 06:10:44
 * @author wangganggang
 */
public class SysLog extends Model{

	/** 主键 */
    private Long logId;

    /** log_type - 日志类型 */
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
    /** exception - 请求异常 */
    private String exception;
    /** agent_user - 用户代理 */
    private String agentUser;

    public Long getLogId(){
        return this.logId;
    }
    public void setLogId(Long logId){
        this.logId = logId;
    }
	public String getLogType(){
        return this.logType;
    }
    public void setLogType(String logType){
        this.logType = logType;
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

    @Override
    public Serializable getPK() {
        return this.logId;
    }
}