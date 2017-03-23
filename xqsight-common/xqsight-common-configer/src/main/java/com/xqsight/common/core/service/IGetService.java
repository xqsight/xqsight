package com.xqsight.common.core.service;

import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IGetService<T, PK extends Serializable> {

    /**
     * judge exist record by filters
     *
     * @param criterion
     * @return
     */
    boolean exists(Criterion criterion);

    /**
     * judge exist record by filters
     *
     * @param propertyFilters
     * @return
     */
    boolean exists(List<PropertyFilter> propertyFilters);

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
     * 根据条件查询零条及多条数据
     *
     * @param propertyFilters 查询条件参数
     * @return 记录列表
     */
    List<T> getByFilters(List<PropertyFilter> propertyFilters);

    /**
     * 根据条件查询零条及多条数据
     *
     * @param propertyFilters 查询条件参数
     * @return 记录列表
     */
    List<T> getByFilters(List<PropertyFilter> propertyFilters, List<Sort> sorts);

    /**
     * 根据条件查询所有记录
     *
     * @return 记录列表
     */
    List<T> getAll();

    /**
     * 根据条件查询所有记录
     *
     * @return 记录列表
     */
    List<T> getAll(List<Sort> sorts);

    /**
     * 根据条件查询一条数据
     *
     * @param criterion 查询条件参数
     * @return 记录列表的第一条数据
     */
    T getOneByCriterion(Criterion criterion);

    /**
     * 根据条件查询一条数据
     *
     * @param propertyFilters 查询条件参数
     * @return 记录列表的第一条数据
     */
    T getOneByFilters(List<PropertyFilter> propertyFilters);
}
