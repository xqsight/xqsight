package com.xqsight.common.core.service;

import com.xqsight.common.core.dao.ICrudDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @param <PK>
 * @author wangganggang
 * @Date 2017/3/23
 * <p>
 * 基本增删改查(CRUD)数据访问服务基类
 */
public abstract class AbstractCrudService<Dao extends ICrudDao<Po, PK>, Po extends BaseModel, PK extends Serializable>
        extends AbstractGetService<Dao, Po, PK> implements ICrudService<Po, PK> {

    @Override
    public int add(Po record) {
        return this.dao.insert(record);
    }

    @Override
    public int batchAdd(List<Po> records) {
        return this.dao.batchInsert(records);
    }

    @Override
    public int editById(Po record) {
        return this.dao.updateById(record);
    }

    @Override
    public int batchEdit(List<Po> records) {
        return this.dao.batchUpdate(records);
    }

    @Override
    public int removeById(PK id) {
        return this.dao.deleteById(id);
    }

    @Override
    public int removeByIds(List<PK> ids) {
        int count = 0;
        for (PK id : ids) {
            count += this.removeById(id);
        }
        return count;
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
    public boolean exists(Criterion criterion) {
        return super.exists(criterion);
    }

    @Override
    public boolean exists(List<PropertyFilter> propertyFilters) {
        return super.exists(propertyFilters);
    }

    @Override
    public Po getById(PK id) {
        return super.getById(id);
    }

    @Override
    public Po getOneByCriterion(Criterion criterion) {
        return super.getOneByCriterion(criterion);
    }

    @Override
    public Po getOneByFilters(List<PropertyFilter> propertyFilters) {
        return super.getOneByFilters(propertyFilters);
    }

    @Override
    public List<Po> getAll() {
        return super.getAll();
    }

    @Override
    public List<Po> getAll(List<Sort> sorts) {
        return super.getAll(sorts);
    }

    @Override
    public List<Po> getByCriterion(Criterion criterion) {
        return super.getByCriterion(criterion);
    }

    @Override
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters) {
        return super.getByFilters(propertyFilters);
    }

    @Override
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters, List<Sort> sorts) {
        return super.getByFilters(propertyFilters, sorts);
    }
}
