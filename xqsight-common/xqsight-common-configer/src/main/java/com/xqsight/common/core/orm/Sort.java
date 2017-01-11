package com.xqsight.common.core.orm;

import org.apache.commons.lang3.StringUtils;

/**
 * SQL 排序对象
 */
public class Sort {

    // 公共变量
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    //排序字段
    private String orderBy;
    //排序方式
    private String order;

    public Sort() {

    }

    public Sort(String orderBy, String order) {
        this.orderBy = orderBy;
        setOrder(order);
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        if (StringUtils.isBlank(order)) {
            this.order = DESC;
            return;
        }
        if (ASC.equalsIgnoreCase(order)) {
            this.order = ASC;
            return;
        }
        this.order = DESC;
    }
}
