package com.xqsight.common.upload.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by wangganggang on 2017/1/11.
 */
@Component
public class UploadConfige {

    /** **/
    private String storePath = "/";
    /** **/
    private String uploadMethod = "ftp";
    /** **/
    private String displayPath = "/display";

    /**
     * ftp IP
     **/
    private String ftpHostName="182.61.6.205";
    /**
     * ftp 端口
     **/
    private Integer ftpPort=21;
    /**
     * ftp 用户
     **/
    private String ftpUserName="ftpuser";
    /**
     * ftp 密码
     **/
    private String ftpPassword="!wgg2016";


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
