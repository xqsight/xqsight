package com.xqsight.common.core.dao;

import java.util.List;

/**
 * @param <T> Po
 */
public interface IUpdateDao<T> {

    /**
     * 根据主键更新用户信息
     *
     * @param record
     * @return 影响的记录数
     */
    int updateById(final T record);

    /**
     * @param records
     * @return 影响的记录数
     */
    int batchUpdate(final List<T> records);
}
