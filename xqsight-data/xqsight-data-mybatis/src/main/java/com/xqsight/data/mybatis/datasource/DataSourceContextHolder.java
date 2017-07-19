package com.xqsight.data.mybatis.datasource;

import com.xqsight.data.mybatis.enums.DataSourceEnum;

/**
 * Created by wangganggang on 2017/7/18.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> contextHolder = new ThreadLocal<>();

    public static void setDataSource(DataSourceEnum dataSource) {
        if(dataSource == null){
            throw new NullPointerException();
        }
        contextHolder.set(dataSource);
    }

    public static DataSourceEnum getDataSource() {
        return contextHolder.get() == null ? DataSourceEnum.MASTER : contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
