package com.xqsight.common.core.dao;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <T>
 */
public interface IUpdateDao<T> {

    /**
     * update record by id
     *
     * @param record
     * @return 影响的记录数
     */
    int updateById(final T record);

    /**
     * batch update record by id
     *
     * @param records
     * @return 影响的记录数
     */
    int batchUpdate(final List<T> records);
}
