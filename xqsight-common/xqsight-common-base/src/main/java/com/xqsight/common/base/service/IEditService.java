package com.xqsight.common.base.service;


import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <T>
 */
public interface IEditService<T> {

    /**
     * 根据主键更新用户信息
     *
     * @param record
     * @return 影响的记录数
     */
    int editById(T record);

    /**
     * batch update recode by id
     * @param records
     * @return 影响的记录数
     */
    int batchEdit(List<T> records);
}
