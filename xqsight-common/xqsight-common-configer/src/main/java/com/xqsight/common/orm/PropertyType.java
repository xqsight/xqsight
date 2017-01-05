package com.xqsight.common.orm;


import com.xqsight.common.model.constants.Constants;

import java.util.Date;


public enum PropertyType {
    S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class), A(String.class);

    private Class<?> clazz;

    PropertyType(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getValue() {
        return clazz;
    }

    public String expressStart() {
        if ("S".equals(this.name())) {
            return "'";
        }
        if ("D".equals(this.name())) {
            return "str_to_date('";
        }
        return "";
    }

    public String expressEnd() {
        if ("S".equals(this.name())) {
            return "'";
        }
        if ("D".equals(this.name())) {
            return "','" + Constants.MYSQL_LONG_TIME_FORMAT + "')";
        }
        return "";
    }
}
