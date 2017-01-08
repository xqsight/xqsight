/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>文件表实体类</p>
 * <p>Table: sys_file - --> SysFile</p>
 * <p>文件表</p>
 * @since 2017-01-07 11:57:19
 * @author wangganggang
 */
public class SysFile extends Model{

	/** 主键 */
    private Long fileId;

    /** file_name - 文件名称 */
    private String fileName;
    /** file_url - 附件URL */
    private String fileUrl;
    /** file_domain - 文件域 */
    private String fileDomain;
    /** file_ext - 扩展名 */
    private String fileExt;
    /** file_size - 大小 */
    private String fileSize;
    /** attachment_type - 附件类型 01:普通图 02:缩略图 03:LOGO */
    private String attachmentType;
    /** file_kind - 附件种类 Image Vedio Doc Excel Ppt */
    private String fileKind;

    public Long getFileId(){
        return this.fileId;
    }
    public void setFileId(Long fileId){
        this.fileId = fileId;
    }
	public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
	public String getFileUrl(){
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl){
        this.fileUrl = fileUrl;
    }
	public String getFileDomain(){
        return this.fileDomain;
    }
    public void setFileDomain(String fileDomain){
        this.fileDomain = fileDomain;
    }
	public String getFileExt(){
        return this.fileExt;
    }
    public void setFileExt(String fileExt){
        this.fileExt = fileExt;
    }
	public String getFileSize(){
        return this.fileSize;
    }
    public void setFileSize(String fileSize){
        this.fileSize = fileSize;
    }
	public String getAttachmentType(){
        return this.attachmentType;
    }
    public void setAttachmentType(String attachmentType){
        this.attachmentType = attachmentType;
    }
	public String getFileKind(){
        return this.fileKind;
    }
    public void setFileKind(String fileKind){
        this.fileKind = fileKind;
    }

    @Override
    public Serializable getPK() {
        return this.fileId;
    }
}