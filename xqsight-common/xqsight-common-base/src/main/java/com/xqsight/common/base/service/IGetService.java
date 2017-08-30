package com.xqsight.common.base.service;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IGetService<T> {

    /**
     * 通过主键找出一条数据
     *
     * @param record 主键id值
     * @return
     */
    T getById(T record);

    /**
     * 根据实体查询一条
     * @param record
     * @return
     */
    T getOne(T record);

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
