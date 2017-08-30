package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.ISelectDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.model.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @author wangganggang
 * @Date 2017/3/23
 */
public abstract class AbstractGetService<Dao extends ISelectDao<Po>, Po extends BaseModel> implements IGetService<Po> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public Po getById(Po record) {
        logger.debug("getById [data={}]", JSON.toJSON(record));
        return dao.selectByPrimaryKey(record);
    }

    @Override
    public Po getOne(Po record) {
        logger.debug("getOne [data={}]", JSON.toJSON(record));
        return dao.selectOne(record);
    }

    @Override
    public List<Po> get(Po record) {
        logger.debug("get [data={}]", JSON.toJSON(record));
        return dao.select(record);
    }

    @Override
    public List<Po> getAll() {
        return dao.select(null);
    }
}
