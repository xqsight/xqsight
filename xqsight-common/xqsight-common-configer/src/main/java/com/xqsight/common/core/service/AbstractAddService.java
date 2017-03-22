package com.xqsight.common.core.service;

import com.xqsight.common.core.dao.IInsertDao;
import com.xqsight.common.model.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 */
public abstract class AbstractAddService<Dao extends IInsertDao<Po>, Po extends Model> implements IAddService<Po> {

    @Autowired
    protected Dao dao;

    @Override
    public int add(Po record) {
        return this.dao.insert(record);
    }

    @Override
    public int batchAdd(List<Po> records) {
        return this.dao.batchInsert(records);
    }
}
