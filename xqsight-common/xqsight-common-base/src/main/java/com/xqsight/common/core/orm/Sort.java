package com.xqsight.common.core.orm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * SQL 排序对象
 */
@Data
@NoArgsConstructor
public class Sort {

    /** 公共变量 **/
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    /** 排序字段 **/
    private String orderBy;
    /** 排序方式 **/
    private String order;

    public Sort(String orderBy, String order) {
        this.orderBy = orderBy;
        setOrder(order);
    }

    public void setOrder(String order) {
        this.order = StringUtils.equalsIgnoreCase(order,ASC) ? ASC : DESC;
    }
}
