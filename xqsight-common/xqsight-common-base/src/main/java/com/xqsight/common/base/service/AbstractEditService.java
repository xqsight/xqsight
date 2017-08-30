package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.IUpdateDao;
import com.xqsight.common.model.BaseModel;
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
public abstract class AbstractEditService<Dao extends IUpdateDao<Po>, Po extends BaseModel> implements IEditService<Po> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    public int edit(Po record) {
        logger.debug("edit [data={}]", JSON.toJSON(record));
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public int editSelective(Po record) {
        logger.debug("editSelective [data={}]", JSON.toJSON(record));
        return dao.updateByPrimaryKeySelective(record);
    }
}
