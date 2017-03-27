package com.xqsight.common.base.dao;

import com.xqsight.common.core.orm.Criterion;

import java.io.Serializable;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <PK>
 */
public interface IDeleteDao<PK extends Serializable> {

    /**
     * delete record by id
     *
     * @param id id主键值
     * @return 影响的记录数
     */
    int deleteById(final PK id);

    /**
     * delete record by criterion
     *
     * @param criterion 查询条件
     * @return 影响的记录数
     */
    int deleteByCriterion(Criterion criterion);

}

