package com.xqsight.upload.model.vo;

import com.xqsight.upload.model.SysFile;

/**
 * Created by user on 2016/9/13.
 */
public class SysFileVo extends SysFile {
    /** UPLOAD_URL - 上传URL */
    private String uploadUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

}
