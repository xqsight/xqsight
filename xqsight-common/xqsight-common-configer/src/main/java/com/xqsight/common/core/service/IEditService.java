package com.xqsight.common.core.service;


import java.util.List;

public interface IEditService<T> {

    /**
     * 根据主键更新用户信息
     *
     * @param record
     * @return 影响的记录数
     */
    int editById(T record);

    /**
     * @param records
     * @return 影响的记录数
     */
    int batchEdit(List<T> records);
}
