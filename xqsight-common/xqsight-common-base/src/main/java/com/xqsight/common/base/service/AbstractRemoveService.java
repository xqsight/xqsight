package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.IDeleteDao;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.model.BaseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @param <Dao>
 * @param <PO>
 * @author wangganggang
 * @Date 2017/3/23
 */
public abstract class AbstractRemoveService<Dao extends IDeleteDao<PO>, PO, Example> implements IRemoveService<PO, Example> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public int removeById(PO record) {
        logger.debug("removeById [record={}]", JSON.toJSON(record));
        return dao.deleteByPrimaryKey(record);
    }

    @Override
    public int remove(PO record) {
        logger.debug("remove [record={}]", JSON.toJSON(record));
        return dao.delete(record);
    }


    /**
     * @param example
     * @return
     */
    @Override
    public int removeByExample(Example example) {
        logger.debug("removeByExample [example={}]", JSON.toJSON(example));
        return dao.deleteByExample(example);
    }

}
