package com.xqsight.common.core.service;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <T>
 */
public interface IAddService<T> {
    /**
     * add record
     *
     * @param record
     * @return
     */
    int add(T record);

    /**
     * batch add record
     *
     * @param records
     * @return
     */
    int batchAdd(List<T> records);
}
