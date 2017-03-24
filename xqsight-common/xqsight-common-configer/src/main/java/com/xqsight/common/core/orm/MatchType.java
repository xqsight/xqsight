package com.xqsight.common.core.orm;

/**
 * @author wangganggang
 * @Date 2017/3/23
 * <p>
 * 属性比较类型. 目前IN 只支持字符串的类型
 */
public enum MatchType {

    NEQ, EQ, LIKE, LT, GT, LE, GE, NULL, NOTNULL, IN, LLIKE, RLIKE;

    public String express() {

        switch (this.name()) {
            case "LIKE":
            case "LLIKE":
            case "RLIKE":
                return "LIKE";
            case "LT":
                return "<";
            case "GT":
                return ">";
            case "LE":
                return "<=";
            case "GE":
                return ">=";
            case "NULL":
                return "is null";
            case "NOTNULL":
                return "is not null";
            case "IN":
                return "in";
            case "NEQ":
                return "<>";
            default:
                return "=";
        }
    }

    public String expressStart() {
        switch (this.name()) {
            case "LIKE":
            case "LLIKE":
                return "%";
            case "IN":
                return "(";
            default:
                return "";
        }
    }

    public String expressEnd() {
        switch (this.name()) {
            case "LIKE":
            case "RLIKE":
                return "%";
            case "IN":
                return ")";
            default:
                return "";
        }
    }

    public boolean outputValue() {
        switch (this.name()) {
            case "NULL":
            case "NOTNULL":
                return false;
            default:
                return true;
        }
    }
}
