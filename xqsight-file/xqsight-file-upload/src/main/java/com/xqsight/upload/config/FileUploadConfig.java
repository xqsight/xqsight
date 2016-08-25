package com.xqsight.upload.config;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2016/5/20.
 */
public class FileUploadConfig {

    /**
     * LOCAL : 本地
     * FTP : ftp 服务器
     */
	public static String SAVE_TYPE="LOCAL";

    public static String FTP_URL;

    public static int FTP_PORT;

    public static String FTP_USERNAME;

    public static String FTP_PASSWORD;

    public static String FTP_UPLOADURL;

	public static String LOCAL_UPLOADURL;

	public static String getSAVE_TYPE() {
		return SAVE_TYPE;
	}
	public static void setSAVE_TYPE(String sAVE_TYPE) {
		SAVE_TYPE = sAVE_TYPE;
	}
	public static String getFTP_URL() {
		return FTP_URL;
	}
	public static void setFTP_URL(String fTP_URL) {
		FTP_URL = fTP_URL;
	}
	public static int getFTP_PORT() {
		return FTP_PORT;
	}
	public static void setFTP_PORT(int fTP_PORT) {
		FTP_PORT = fTP_PORT;
	}
	public static String getFTP_USERNAME() {
		return FTP_USERNAME;
	}
	public static void setFTP_USERNAME(String fTP_USERNAME) {
		FTP_USERNAME = fTP_USERNAME;
	}
	public static String getFTP_PASSWORD() {
		return FTP_PASSWORD;
	}
	public static void setFTP_PASSWORD(String fTP_PASSWORD) {
		FTP_PASSWORD = fTP_PASSWORD;
	}
	public static String getFTP_UPLOADURL() {
		return FTP_UPLOADURL;
	}
	public static void setFTP_UPLOADURL(String fTP_UPLOADURL) {
		FTP_UPLOADURL = fTP_UPLOADURL;
	}
	public static String getLOCAL_UPLOADURL() {
		return LOCAL_UPLOADURL;
	}
	public static void setLOCAL_UPLOADURL(String lOCAL_UPLOADURL) {
		LOCAL_UPLOADURL = lOCAL_UPLOADURL;
	}

}
