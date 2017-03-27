package com.xqsight.common.base.dao;

import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <T>
 * @param <PK>
 */
public interface ISelectDao<T, PK extends Serializable> {

    /**
     * select record by id
     *
     * @param id 主键id值
     * @return record
     */
    T selectById(final PK id);

    /**
     * 根据条件查询零条及多条数据
     *
     * @param criterion 查询条件参数
     * @return record list
     */
    List<T> search(Criterion criterion);
}
