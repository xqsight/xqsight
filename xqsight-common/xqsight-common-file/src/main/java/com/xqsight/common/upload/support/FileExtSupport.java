package com.xqsight.common.upload.support;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/6/7.
 */
public class FileExtSupport {
    private static final Map<String, String> extMap = new HashMap<>();

    static {
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
    }

    public static String[] getFileExts(String name) {
        String strExt = getFileExt(name);
        return StringUtils.split(strExt, ",");
    }

    public static String getFileExt(String name) {
        name = StringUtils.isBlank(name) ? "image" : name;
        return MapUtils.getString(extMap, name);
    }

    /**
     * 检查视频类型
     *
     * @param ext
     * @return ffmpeg 能解析返回0，不能解析返回1
     */
    public static int checkIsConvertVedio(String ext) {
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        switch (ext.toLowerCase()) {
            case "avi":
            case "mpg":
            case "wmv":
            case "3gp":
            case "mov":
            case "mp4":
            case "asf":
            case "asx":
            case "flv":
                return 0;
            case "wmv9":
            case "rm":
            case "rmvb":
                return 1;
            default:
                return 9;
        }
    }

    /**
     * 获取文件存储类型
     *
     * @param ext
     * @return
     */
    public static String fileKind(String ext) {
        String fileKindPath;
        switch (ext.toLowerCase()) {
            case "mp4":
            case "avi":
                fileKindPath = "vedios";
                break;
            case "doc":
            case "docx":
                fileKindPath = "word";
                break;
            case "xls":
            case "xlsx":
                fileKindPath = "excel";
                break;
            case "ppt":
            case "pptx":
                fileKindPath = "ppt";
                break;
            case "cvs":
            case "pdf":
                fileKindPath = "pdf";
                break;
            case "text":
                fileKindPath = "text";
                break;
            default:
                fileKindPath = "images";
                break;
        }
        return fileKindPath;
    }
}
