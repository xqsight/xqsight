package com.xqsight.common.base.service;

import com.xqsight.common.base.dao.IInsertDao;
import com.xqsight.common.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * @param <Dao>
 * @param <Po>
 */
public abstract class AbstractAddService<Dao extends IInsertDao<Po>, Po extends BaseModel> implements IAddService<Po> {

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
