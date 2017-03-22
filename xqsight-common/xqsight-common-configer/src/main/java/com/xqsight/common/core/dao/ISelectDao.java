package com.xqsight.common.core.dao;

import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> Po
 */
public interface ISelectDao<T, PK extends Serializable> {

    /**
     * 通过主键找出一条数据
     *
     * @param id 主键id值
     * @return
     */
    T selectById(final PK id);

    /**
     * 根据条件查询零条及多条数据
     *
     * @param criterion 查询条件参数
     * @return 记录列表
     */
    List<T> search(Criterion criterion);
}
