package com.xqsight.common.base.service;

import com.xqsight.common.base.dao.IDeleteDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <Dao>
 * @param <PK>
 */
public abstract class AbstractRemoveService<Dao extends IDeleteDao<PK>, PK extends Serializable> implements IRemoveService<PK> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public int removeById(PK id) {
        return this.dao.deleteById(id);
    }

    @Override
    public int removeByCriterion(Criterion criterion) {
        return this.dao.deleteByCriterion(criterion);
    }

    @Override
    public int removeByFilters(List<PropertyFilter> propertyFilters) {
        return this.removeByCriterion(new Criterion(propertyFilters));
    }

    @Override
    public int removeByIds(List<PK> ids) {
        int count = 0;
        for (PK id : ids) {
            count += this.removeById(id);
        }
        return count;
    }
}
