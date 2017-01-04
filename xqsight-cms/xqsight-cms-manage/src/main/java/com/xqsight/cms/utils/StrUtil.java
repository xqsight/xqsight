package com.xqsight.cms.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangganggang on 2017/1/4.
 */
public class StrUtil extends com.xiaoleilu.hutool.util.StrUtil {
    /**
     * 获取前面的部分,如果有汉子,怎提取汉子部分
     * @param content
     * @param count
     * @return
     */
    public static String pickPrefixContent(String content,int count){
        if(isBlank(content))
            return "";
        String prefixContent = getChinese(content, count);

        if (StringUtils.isBlank(prefixContent))
            prefixContent = content.length() > 20 ? content.substring(0, 20) : content;

        return prefixContent;
    }

    /**
     * 获取字符串的前count个汉字
     *
     * @param str
     * @param count
     * @return
     */
    public static String getChinese(String str,int count){
        if(isBlank(str))
            return "";
        Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]+)");
        Matcher matcher = pattern.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            stringBuffer.append(matcher.group(0));
            if(stringBuffer.length() > count)
                break;
        }

        if(stringBuffer.length()<= count)
            return stringBuffer.toString();

        return  stringBuffer.substring(0,count);
    }
}
