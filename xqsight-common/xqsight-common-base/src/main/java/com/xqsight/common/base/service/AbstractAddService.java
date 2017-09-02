package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.IInsertDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @author wangganggang
 * @Date 2017/3/23
 */
public abstract class AbstractAddService<Dao extends IInsertDao<Po>, Po> implements IAddService<Po> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public int save(Po record) {
        logger.debug("save [record={}]", JSON.toJSON(record));
        return dao.insert(record);
    }

    @Override
    public int batchSave(List<Po> records) {
        logger.debug("batchSave [records={}]", JSON.toJSON(records));
        return dao.insertList(records);
    }

    @Override
    public int saveSelective(Po record) {
        logger.debug("saveSelective [record={}]", JSON.toJSON(record));
        return dao.insertSelective(record);
    }
}
