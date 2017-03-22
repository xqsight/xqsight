package com.xqsight.common.core.service;

import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IGetService<T, PK extends Serializable> {
    /**
     * @param criterion
     * @return
     */
    boolean exists(Criterion criterion);

    /**
     * 通过主键找出一条数据
     *
     * @param id 主键id值
     * @return
     */
    T getById(PK id);

    /**
     * 根据条件查询零条及多条数据
     *
     * @param criterion 查询条件参数
     * @return 记录列表
     */
    List<T> getByCriterion(Criterion criterion);

    /**
     * 根据条件查询所有记录
     *
     * @return 记录列表
     */
    List<T> getAll();

    /**
     * 根据条件查询一条数据
     *
     * @param criterion 查询条件参数
     * @return 分页记录列表
     */
    T getOneByCriterion(Criterion criterion);
}
