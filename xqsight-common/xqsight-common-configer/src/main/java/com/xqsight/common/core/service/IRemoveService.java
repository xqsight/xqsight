package com.xqsight.common.core.service;

import com.xqsight.common.core.orm.Criterion;

import java.util.List;

public interface IRemoveService<PK> {
    /**
     * @param id
     * @return
     */
    int removeById(PK id);

    /**
     * @param ids
     * @return
     */
    int removeByIds(List<PK> ids);

    /**
     * @param criterion
     * @return
     */
    int removeByCriterion(Criterion criterion);
}
