package com.xqsight.common.base.service;

/**
 * @param <T>  Po
 * @param <U>  Example
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IRemoveService<T, U> {

    /**
     * delete record by id
     *
     * @param record
     * @return
     */
    int removeById(T record);


    /**
     * 根据内容删除
     *
     * @param record
     * @return
     */
    int remove(T record);

    /**
     * @param example
     * @return
     */
    int removeByExample(U example);

}
