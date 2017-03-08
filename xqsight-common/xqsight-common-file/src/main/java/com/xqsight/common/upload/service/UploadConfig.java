package com.xqsight.common.upload.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangganggang on 2017/1/11.
 */
@Component
public class UploadConfig {

    /** 文件上传方式 **/
    @Value("${file.upload.method}")
    private String uploadMethod = "local";

    /** 文件保存路径**/
    @Value("${file.upload.store.path}")
    private String storePath;
    /** 文件访问路径 **/
    @Value("${file.upload.access.domain}")
    private String displayPath;


    /**  ftp IP **/
    @Value("${file.upload.ftp.host:localhost}")
    private String ftpHostName;

    /**  ftp 端口  **/
    @Value("${file.upload.ftp.port:21}")
    private Integer ftpPort;

    /**  ftp 用户 **/
    @Value("${file.upload.ftp.username:ftp}")
    private String ftpUserName;

    /**  ftp 密码 **/
    @Value("${file.upload.ftp.password:ftppwd}")
    private String ftpPassword;


    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getUploadMethod() {
        return uploadMethod;
    }

    public void setUploadMethod(String uploadMethod) {
        this.uploadMethod = uploadMethod;
    }

    public String getFtpHostName() {
        return ftpHostName;
    }

    public void setFtpHostName(String ftpHostName) {
        this.ftpHostName = ftpHostName;
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getDisplayPath() {
        return displayPath;
    }

    public void setDisplayPath(String displayPath) {
        this.displayPath = displayPath;
    }

    public boolean isFtpMethod() {
        return StringUtils.equalsIgnoreCase(uploadMethod, "ftp") ? true : false;
    }
}
