/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.upload.model;

import com.xqsight.common.model.BaseModel;

/**
 * <p>文件表实体类</p>
 * <p>Table: SYS_FILE - --> SysFile</p>
 * <p>文件表</p>
 * @since 2016-05-09 08:16:30
 */
public class SysFile extends BaseModel {

	/** 主键 */
    private Long fileId;
	/** FILE_NAME - 文件名称 */
    private String fileName;
	/** FILE_URL - 附件URL */
    private String fileUrl;
    /** FILE_DEMAIN - 文件前缀 */
    private String fileDomain;
	/** FILE_EXT - 扩展名 */
    private String fileExt;
	/** FILE_SIZE - 大小 */
    private String fileSize;
	/** ATTACHMENT_TYPE - 附件类型 */
    private String attachmentType;
	/** FILE_KIND - 附件种类  */
    private String fileKind;

    private String fullPath;

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
	public String getFileDomain() {
		return fileDomain;
	}
	public void setFileDomain(String fileDomain) {
		this.fileDomain = fileDomain;
	}
    public String getFullPath() {
        return fileUrl;
    }
    public void setFullPath(String fullPath) {
        this.fullPath = fileUrl;
    }
}