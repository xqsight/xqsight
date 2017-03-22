package com.xqsight.common.core.dao;

import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;

public interface IDeleteDao<PK extends Serializable> {

    /**
     * 根据主键删除记录
     *
     * @param id id主键值
     * @return 影响的记录数
     */
    int deleteById(final PK id);

    /**
     * 根据条件删除记录
     *
     * @param criterion 查询Example条件参数
     * @return 影响的记录数
     */
    int deleteByCriterion(Criterion criterion);

}

