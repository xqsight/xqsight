package com.xqsight.upload.model.vo;

import com.xqsight.upload.model.SysFile;

/**
 * Created by user on 2016/9/13.
 */
public class SysFileVo extends SysFile {
    /** UPLOAD_URL - 上传URL */
    private String uploadUrl;
    /** FILE_DESCRITION - 描述 */
    private String fileDescription;
    /** FILE_THUMBNAILS - 缩略图 */
    private String fileThumbnails;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getFileDescription() {
        return fileDescription;
    }
    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }
    public String getFileThumbnails() {
        return fileThumbnails;
    }
    public void setFileThumbnails(String fileThumbnails) {
        this.fileThumbnails = fileThumbnails;
    }

}
