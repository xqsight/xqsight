package com.xqsight.common.core.service;

import com.xqsight.common.core.dao.IDeleteDao;
import com.xqsight.common.core.orm.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Dao>
 */
public abstract class AbstractRemoveService<Dao extends IDeleteDao<PK>, PK extends Serializable> implements IRemoveService<PK> {

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
    public int removeByIds(List<PK> ids) {
        int count = 0;
        for (PK id : ids) {
            count += this.removeById(id);
        }
        return count;
    }
}
