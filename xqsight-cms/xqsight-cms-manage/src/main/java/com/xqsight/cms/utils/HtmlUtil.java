package com.xqsight.cms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangganggang on 2017/1/4.
 */
public class HtmlUtil extends com.xiaoleilu.hutool.util.HtmlUtil {
    public static final String RE_HTML_IMG = "(?:src=\"?)(.*?)\"?\\s";

    /**
     * 获取内容中图片
     *
     * @param htmlContent
     * @return
     */
    public static List<String> pickImg(String htmlContent) {
        Pattern pattern = Pattern.compile(RE_HTML_IMG);
        Matcher matcher = pattern.matcher(htmlContent);
        List<String> imgList = new ArrayList<String>();
        while (matcher.find()) {
            imgList.add(matcher.group(1));
        }
        return imgList;
    }

    /**
     * 移除内容中的图片
     *
     * @param htmlContent
     * @return
     */
    public static String removeImg(String htmlContent) {
        return htmlContent.replaceAll(RE_HTML_IMG, "");
    }
}
