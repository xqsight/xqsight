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
 * @author wangganggang
 * @Date 2017/3/23
 * <p>
 * 基本增删改查(CRUD)数据访问服务基类
 */
@Transactional
public abstract class AbstractCrudService<Dao extends ICrudDao<Po>, Po extends BaseModel>
        extends AbstractGetService<Dao, Po> implements ICrudService<Po> {

    @Override
    @Transactional
    public int save(Po record) {
        return this.dao.insert(record);
    }

    @Override
    @Transactional
    public int saveSelective(Po record) {
        return this.dao.insertSelective(record);
    }

    @Override
    @Transactional
    public int batchSave(List<Po> records) {
        return this.dao.insertList(records);
    }

    @Override
    @Transactional
    public int edit(Po record) {
        return this.dao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int editSelective(Po record) {
        return this.dao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int remove(Po record) {
        return this.dao.delete(record);
    }

    @Override
    @Transactional
    public int removeById(Po record) {
        return this.dao.deleteByPrimaryKey(record);
    }

}
