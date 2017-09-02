package com.xqsight.common.base.service;


import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @param <T> Po
 * @param <U> Example
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IEditService<T, U> {

    /**
     * 根据主键修改
     *
     * @param record
     * @return 影响的记录数
     */
    int edit(T record);

    /**
     * 根据主键修改
     * if null not update
     *
     * @param record
     * @return
     */
    int editSelective(T record);


    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return 影响的记录数
     */
    int editByExample(@Param("record") T record, @Param("example") U example);

    /**
     * @param records
     * @return 影响的记录数
     */
    int batchEdit(@Param("records") List<T> records);
}
