package com.xqsight.common.core.dao;


import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;
import java.util.List;

public interface Dao<T, PK extends Serializable> {

    int deleteByPrimaryKey(final PK id);


    int insert(final T t);


    int insertSelective(final T t);


    T selectByPrimaryKey(final PK id);


    int updateByPrimaryKeySelective(final T t);


    int updateByPrimaryKey(final T t);

    /**
     * 多条件查询
     */
    List<T> find(Criterion criterion);

    /**
     * 计算总记录数
     *
     * @param criterion
     * @return
     */
    long count(Criterion criterion);
}
