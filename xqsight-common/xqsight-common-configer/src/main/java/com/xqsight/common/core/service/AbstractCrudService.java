package com.xqsight.common.core.service;

import com.xqsight.common.core.dao.ICrudDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.model.Model;

import java.io.Serializable;
import java.util.List;

/**
 * 基本增删改查(CRUD)数据访问服务基类
 *
 * @param <Dao>
 * @param <Po>
 */
public abstract class AbstractCrudService<Dao extends ICrudDao<Po, PK>, Po extends Model, PK extends Serializable>
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
