package com.xqsight.common.base.service;

import com.xqsight.common.base.dao.ICrudDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.model.BaseModel;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public abstract class AbstractCrudService<Dao extends ICrudDao<Po, PK>, Po extends BaseModel, PK extends Serializable>
        extends AbstractGetService<Dao, Po, PK> implements ICrudService<Po, PK> {

    @Override
    @Transactional
    public int add(Po record) {
        return this.dao.insert(record);
    }

    @Override
    @Transactional
    public int batchAdd(List<Po> records) {
        return this.dao.batchInsert(records);
    }

    @Override
    @Transactional
    public int editById(Po record) {
        return this.dao.updateById(record);
    }

    @Override
    @Transactional
    public int batchEdit(List<Po> records) {
        return this.dao.batchUpdate(records);
    }

    @Override
    @Transactional
    public int removeById(PK id) {
        return this.dao.deleteById(id);
    }

    @Override
    @Transactional
    public int removeByIds(List<PK> ids) {
        int count = 0;
        for (PK id : ids) {
            count += this.removeById(id);
        }
        return count;
    }

    @Override
    @Transactional
    public int removeByCriterion(Criterion criterion) {
        return this.dao.deleteByCriterion(criterion);
    }

    @Override
    @Transactional
    public int removeByFilters(List<PropertyFilter> propertyFilters) {
        return this.removeByCriterion(new Criterion(propertyFilters));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Criterion criterion) {
        return super.exists(criterion);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(List<PropertyFilter> propertyFilters) {
        return super.exists(propertyFilters);
    }

    @Override
    @Transactional(readOnly = true)
    public Po getById(PK id) {
        return super.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Po getOneByCriterion(Criterion criterion) {
        return super.getOneByCriterion(criterion);
    }

    @Override
    @Transactional(readOnly = true)
    public Po getOneByFilters(List<PropertyFilter> propertyFilters) {
        return super.getOneByFilters(propertyFilters);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getAll() {
        return super.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getAll(List<Sort> sorts) {
        return super.getAll(sorts);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getByCriterion(Criterion criterion) {
        return super.getByCriterion(criterion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters) {
        return super.getByFilters(propertyFilters);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getByFilters(List<PropertyFilter> propertyFilters, List<Sort> sorts) {
        return super.getByFilters(propertyFilters, sorts);
    }
}
