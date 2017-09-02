package com.xqsight.common.base.service;

import java.util.List;

/**
 * @param <T>  Po
 * @param <U>  Example
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IGetService<T, U> {

    /**
     * @param example
     * @return
     */
    boolean exists(U example);

    /**
     * 通过主键找出一条数据
     *
     * @param record 主键id值
     * @return
     */
    T getById(T record);

    /**
     * 根据条件查询零条及多条数据
     *
     * @param example 查询条件参数
     * @return 记录列表
     */
    List<T> getByExample(U example);

    /**
     * 根据条件查询一条数据
     *
     * @param example 查询条件参数
     * @return 分页记录列表
     */
    T getOneByExample(U example);

    /**
     * 根据实体对象查询
     *
     * @param record 查询条件参数
     * @return 记录列表
     */
    List<T> get(T record);

    /**
     * 根据条件查询所有记录
     *
     * @return 记录列表
     */
    List<T> getAll();
}
