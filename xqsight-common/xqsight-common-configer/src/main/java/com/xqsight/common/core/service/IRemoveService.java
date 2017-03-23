package com.xqsight.common.core.service;

import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <PK>
 */
public interface IRemoveService<PK> {

    /**
     * delete record by id
     * @param id
     * @return
     */
    int removeById(PK id);

    /**
     * batch delete recode by ids
     * @param ids
     * @return
     */
    int removeByIds(List<PK> ids);

    /**
     * delete record by criterion
     * @param criterion
     * @return
     */
    int removeByCriterion(Criterion criterion);

    /**
     * delete record by filters
     * @param propertyFilters
     * @return
     */
    int removeByFilters(List<PropertyFilter> propertyFilters);
}
