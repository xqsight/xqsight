/**
 * 新启信息技术有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.commons.utils;

import java.math.BigDecimal;

/**
 * 
 * @author xqsight-jerry
 * @version MoneyUtils.java, v 0.1 2015年5月27日 下午3:52:05 xqsight-jerry
 */
public class MoneyUtils {

    //    private final static BigDecimal HUNDRED = new BigDecimal(100);

    private final static BigDecimal THOUSAND = new BigDecimal(1000);

    /**
     * 厘转分
     * @param val
     * @return
     */
    public static String milliToCent(BigDecimal val) {
        return val.divide(BigDecimal.TEN, 0, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 分转厘
     * @param val
     * @return
     */
    public static BigDecimal centToMilli(BigDecimal val) {
        return val.multiply(BigDecimal.TEN).setScale(0);
    }

    /**
     * 分转厘
     * @param val
     * @return
     */
    public static BigDecimal centToMilli(String val) {
        return new BigDecimal(val).multiply(BigDecimal.TEN).setScale(0);
    }

    /**
     * 元转厘
     * @param val
     * @return
     */
    public static BigDecimal dollarToMilli(BigDecimal val) {
        return val.multiply(THOUSAND).setScale(0);
    }

    /**
     * 元转厘
     * @param val
     * @return
     */
    public static BigDecimal dollarToMilli(String val) {
        return new BigDecimal(val).multiply(THOUSAND).setScale(0);
    }

    /**
     * 厘转元 -- 精确到元
     * @param val
     * @return
     */
    public static String milliToDollar(BigDecimal val) {
        return milliToDollar(val, 0);
    }

    /**
     * 厘转元 -- 保留scale位小数
     * @param val
     * @return
     */
    public static String milliToDollar(BigDecimal val, int scale) {
        return val.divide(THOUSAND, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 厘转元 -- 保留scale位小数
     * @param val
     * @return
     */
    public static BigDecimal milliToDollarDecimal(BigDecimal val, int scale) {
        return val.divide(THOUSAND, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 厘转元 -- 保留scale位小数
     * @param val
     * @param scale
     * @return
     */
    public static BigDecimal milliToDollarDecimal(String val, int scale) {
        return new BigDecimal(val).divide(THOUSAND, scale, BigDecimal.ROUND_HALF_UP);
    }

}
