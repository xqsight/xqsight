package com.xqsight.common.core.orm;


import java.util.Date;


/**
 * @author wangganggang
 * @Date 2017/3/23
 */
public enum PropertyType {

    S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class), A(String.class);

    private static final String MYSQL_LONG_TIME_FORMAT = "%Y-%m-%d %H:%i:%s";

    private Class<?> clazz;

    PropertyType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getValue() {
        return clazz;
    }

    public String expressStart() {
        switch (this.name()) {
            case "S":
                return "'";
            case "D":
                return "str_to_date('";
            default:
                return "";
        }
    }

    public String expressEnd() {
        switch (this.name()) {
            case "S":
                return "'";
            case "D":
                return "','" + MYSQL_LONG_TIME_FORMAT + "')";
            default:
                return "";
        }
    }
}
