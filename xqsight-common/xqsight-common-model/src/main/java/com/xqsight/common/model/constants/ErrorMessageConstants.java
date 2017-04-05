/**
 *
 */
package com.xqsight.common.model.constants;

import java.util.ListResourceBundle;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 错误代码对照表
 *
 */
@Deprecated
public class ErrorMessageConstants extends ListResourceBundle {

    /* ERR_MSG_ */
    public static final String ERR_MSG_0001 = "ERR_MSG_0001";
    public static final String ERR_MSG_0002 = "ERR_MSG_0002";
    public static final String ERR_MSG_0003 = "ERR_MSG_0003";
    public static final String ERR_MSG_0004 = "ERR_MSG_0004";
    public static final String ERR_MSG_0005 = "ERR_MSG_0005";
    public static final String ERR_MSG_0006 = "ERR_MSG_0006";
    public static final String ERR_MSG_0007 = "ERR_MSG_0007";
    public static final String ERR_MSG_0008 = "ERR_MSG_0008";
    public static final String ERR_MSG_0009 = "ERR_MSG_0009";
    public static final String ERR_MSG_0010 = "ERR_MSG_0010";
    //未知异常
    public static final String ERR_MSG_9999 = "ERR_MSG_9999";


    private static ResourceBundle rb = null;

    public static String[][] errMsg = null;

    static {
        rb = PropertyResourceBundle.getBundle("errMessage");
        Set<String> keys = rb.keySet();
        int size = rb.keySet().size();
        int i = 0;
        errMsg = new String[size][2];
        for (String key : keys) {
            errMsg[i][0] = key;
            errMsg[i++][1] = rb.getString(key);
        }
    }

    @Override
    protected Object[][] getContents() {
        return ErrorMessageConstants.errMsg;
    }

    /**
     * 是否有某个出错信息代码
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return rb.containsKey(key);
    }

    /**
     * 获取出错信息
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return rb.containsKey(key) ? rb.getString(key) : "";
    }

    /**
     * 获取出错信息 key:value 形式
     *
     * @param key
     * @return
     */
    public static String getKeyValue(String key) {
        return key + ":" + rb.getString(key);
    }

}
