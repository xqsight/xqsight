package com.xqsight.common.core.service;

import com.xqsight.common.core.dao.ISelectDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 */
public abstract class AbstractGetService<Dao extends ISelectDao<Po, PK>, Po extends Model, PK extends Serializable> implements IGetService<Po, PK> {

    @Autowired
    protected Dao dao;

    @Override
    public boolean exists(Criterion criterion) {
        List<Po> pos = this.dao.search(criterion);
        return pos.size() == 0 ? false : true;
    }

    @Override
    public Po getById(PK id) {
        return this.dao.selectById(id);
    }

    @Override
    public List<Po> getByCriterion(Criterion criterion) {
        return this.dao.search(criterion);
    }

    @Override
    public List<Po> getAll() {
        return this.dao.search(null);
    }

    @Override
    public Po getOneByCriterion(Criterion criterion) {
        List<Po> pos = this.dao.search(criterion);
        if (pos.size() > 0) {
            return pos.get(0);
        }
        return null;
    }
}
