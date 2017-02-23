/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqsight.common.model.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>招聘表实体类</p>
 * <p>Table: cms_job - --> CmsJob</p>
 * <p>招聘表</p>
 * @since 2017-02-23 04:52:11
 * @author wangganggang
 */
public class CmsJob extends Model{

	/** 主键 */
    private Long jobId;

    /** position_id - 职位内码 */
    private Long positionId;
    /** job_name - 招聘名称 */
    private String jobName;
    /** job_start_time - 招聘开始时间 */
    private LocalDateTime jobStartTime;
    /** job_end_time - 招聘结束时间 */
    private LocalDateTime jobEndTime;
    /** job_content - 招聘内容 */
    private String jobContent;
    /** job_phone - 电话 */
    private String jobPhone;
    /** status - 状态 1:正常2:结束3:4: */
    private Byte status;
    /** job_address - 地点 */
    private String jobAddress;

    public Long getJobId(){
        return this.jobId;
    }
    public void setJobId(Long jobId){
        this.jobId = jobId;
    }
	public Long getPositionId(){
        return this.positionId;
    }
    public void setPositionId(Long positionId){
        this.positionId = positionId;
    }
	public String getJobName(){
        return this.jobName;
    }
    public void setJobName(String jobName){
        this.jobName = jobName;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getJobStartTime(){
        return this.jobStartTime;
    }
    public void setJobStartTime(LocalDateTime jobStartTime){
        this.jobStartTime = jobStartTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getJobEndTime(){
        return this.jobEndTime;
    }
    public void setJobEndTime(LocalDateTime jobEndTime){
        this.jobEndTime = jobEndTime;
    }
	public String getJobContent(){
        return this.jobContent;
    }
    public void setJobContent(String jobContent){
        this.jobContent = jobContent;
    }
	public String getJobPhone(){
        return this.jobPhone;
    }
    public void setJobPhone(String jobPhone){
        this.jobPhone = jobPhone;
    }
	public Byte getStatus(){
        return this.status;
    }
    public void setStatus(Byte status){
        this.status = status;
    }
	public String getJobAddress(){
        return this.jobAddress;
    }
    public void setJobAddress(String jobAddress){
        this.jobAddress = jobAddress;
    }

    @Override
    public Serializable getPK() {
        return this.jobId;
    }
}