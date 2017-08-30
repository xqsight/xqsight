package com.xqsight.common.base.service;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <T>
 */
public interface IAddService<T> {
    /**
     * save record
     *
     * @param record
     * @return
     */
    int save(T record);

    /**
     * batch save record
     *
     * @param records
     * @return
     */
    int batchSave(List<T> records);

    /**
     * save record
     * if null not save
     * @param record
     * @return
     */
    int saveSelective(T record);
}
