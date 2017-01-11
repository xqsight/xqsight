package com.xqsight.common.core.orm;

/**
 * 属性比较类型. 目前IN 只支持字符串的类型
 */
public enum MatchType {
    NEQ, EQ, LIKE, LT, GT, LE, GE, NULL, NOTNULL, IN, LLIKE, RLIKE;

    public String express() {
        if ("LIKE".equals(this.name()) || "LLIKE".equals(this.name()) || "RLIKE".equals(this.name())) {
            return "LIKE";
        }
        if ("LT".equals(this.name())) {
            return "<";
        }
        if ("GT".equals(this.name())) {
            return ">";
        }
        if ("LE".equals(this.name())) {
            return "<=";
        }
        if ("GE".equals(this.name())) {
            return ">=";
        }
        if ("NULL".equals(this.name())) {
            return "is null";
        }
        if ("NOTNULL".equals(this.name())) {
            return "is not null";
        }
        if ("IN".equals(this.name())) {
            return "in";
        }
        if ("NEQ".equals(this.name())) {
            return "<>";
        }
        return "=";
    }

    public String expressStart() {
        if ("LIKE".equals(this.name()) || "LLIKE".equals(this.name())) {
            return "%";
        }
        if ("IN".equals(this.name())) {
            return "(";
        }
        return "";
    }

    public String expressEnd() {
        if ("LIKE".equals(this.name()) || "RLIKE".equals(this.name())) {
            return "%";
        }
        if ("IN".equals(this.name())) {
            return ")";
        }
        return "";
    }

    public boolean outputValue() {
        if ("NULL".equals(this.name()) || "NOTNULL".equals(this.name())) {
            return false;
        }
        return true;
    }
}
