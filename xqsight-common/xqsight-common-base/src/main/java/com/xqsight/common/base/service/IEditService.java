package com.xqsight.common.base.service;


/**
 * @param <T>
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IEditService<T> {

    /**
     * 根据主键修改
     *
     * @param record
     * @return 影响的记录数
     */
    int edit(T record);

    /**
     * 根据逐渐修改
     * if null not update
     *
     * @param record
     * @return
     */
    int editSelective(T record);
}
