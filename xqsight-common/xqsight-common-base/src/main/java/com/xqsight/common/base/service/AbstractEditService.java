package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.IUpdateDao;
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
public abstract class AbstractEditService<Dao extends IUpdateDao<Po>, Po, Example> implements IEditService<Po, Example> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public int edit(Po record) {
        logger.debug("edit [record={}]", JSON.toJSON(record));
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public int editSelective(Po record) {
        logger.debug("editSelective [record={}]", JSON.toJSON(record));
        return dao.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return 影响的记录数
     */
    @Override
    public int editByExample(Po record, Example example) {
        logger.debug("editByExample [record={}],[example={}]", JSON.toJSON(record), JSON.toJSON(example));
        return dao.updateByExample(record, example);
    }

    /**
     * @param records
     * @return 影响的记录数
     */
    @Override
    public int batchEdit(List<Po> records) {
        logger.debug("batchEdit [records={}]", JSON.toJSON(records));
        int ret = 0;
        for (Po po : records) {
            ret += dao.updateByPrimaryKeySelective(po);
        }
        return ret;
    }
}
