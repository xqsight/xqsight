package com.xqsight.upload.config;

import com.xqsight.common.model.constants.CacheKeyConstants;
import com.xqsight.upload.FileConstants;
import com.xqsight.commons.web.SpringContextHolder;
import com.xqsight.data.ehcache.core.CacheTemplate;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by user on 2016/5/20.
 */
public class FileUploadConfig {

    private static Logger logger = LogManager.getLogger(FileUploadConfig.class);

    private static CacheTemplate cacheTemplate = SpringContextHolder.getBean(CacheTemplate.class);

    static {
        Map<String, Map<String, String>> dictMap = (Map<String, Map<String, String>>) cacheTemplate.get(CacheKeyConstants.SYS_DICT_MAP);

        if (dictMap == null) {
            logger.error("当前系统缓存为空");
        }

        Map<String, String> detailDictMap = dictMap.get(FileConstants.FTP_CONFIGER);
        if (detailDictMap == null) {
            logger.error("没有配置文件上传信息");
        }

        SAVE_TYPE = MapUtils.getString(detailDictMap, FileConstants.SAVE_TYPE, "local");
        FTP_URL = MapUtils.getString(detailDictMap, FileConstants.FTP_URL, "");
        FTP_PORT = MapUtils.getString(detailDictMap, FileConstants.FTP_PORT, "0");
        FTP_USERNAME = MapUtils.getString(detailDictMap, FileConstants.FTP_USERNAME, "");
        FTP_PASSWORD = MapUtils.getString(detailDictMap, FileConstants.FTP_PASSWORD, "");
        FTP_UPLOADURL = MapUtils.getString(detailDictMap, FileConstants.FTP_UPLOADURL, "");
        LOCAL_UPLOADURL = MapUtils.getString(detailDictMap, FileConstants.LOCAL_UPLOADURL, "");
    }

    /**
     * LOCAL : 本地
     * FTP : ftp 服务器
     */
    public static String SAVE_TYPE = "local";

    public static String FTP_URL;

    public static String FTP_PORT;

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

    public static String getFTP_PORT() {
        return FTP_PORT;
    }

    public static void setFTP_PORT(String fTP_PORT) {
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
