package com.xqsight.common.base.service;

import com.xqsight.common.base.dao.ISelectDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <Dao>
 * @param <Po>
 * @param <PK>
 */
public abstract class AbstractGetService<Dao extends ISelectDao<Po, PK>, Po extends BaseModel, PK extends Serializable> implements IGetService<Po, PK> {

    @Autowired
    protected Dao dao;

    @Override
    public boolean exists(Criterion criterion) {
        List<Po> pos = this.dao.search(criterion);
        return pos.size() == 0 ? false : true;
    }

    @Override
    public boolean exists(List<PropertyFilter> propertyFilters) {
        return exists(new Criterion(propertyFilters));
    }

    @Override
    public Po getById(PK id) {
        return this.dao.selectById(id);
    }

    @Override
    public Po getOneByCriterion(Criterion criterion) {
        List<Po> pos = this.dao.search(criterion);
        if (pos.size() > 0) {
            return pos.get(0);
        }
        return null;
    }

    @Override
    public Po getOneByFilters(List<PropertyFilter> propertyFilters) {
        return this.getOneByCriterion(new Criterion(propertyFilters));
    }

    @Override
    public List<Po> getAll() {
        return this.dao.search(null);
    }

    @Override
    public List<Po> getAll(List<Sort> sorts) {
        return this.dao.search(new Criterion(null, sorts));
    }

    @Override
    public List<Po> getByCriterion(Criterion criterion) {
        return this.dao.search(criterion);
    }

    @Override
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters) {
        return this.getByCriterion(new Criterion(propertyFilters));
    }

    @Override
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters, List<Sort> sorts) {
        return this.getByCriterion(new Criterion(propertyFilters, sorts));
    }

}
