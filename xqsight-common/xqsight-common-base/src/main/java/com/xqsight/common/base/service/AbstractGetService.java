package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.base.dao.ISelectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @param <Dao>
 * @param <Po>
 * @author wangganggang
 * @Date 2017/3/23
 */
public abstract class AbstractGetService<Dao extends ISelectDao<Po>, Po, Example> implements IGetService<Po, Example> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected Dao dao;

    @Override
    @Transactional(readOnly = true)
    public Po getById(Po record) {
        logger.debug("getById [record={}]", JSON.toJSON(record));
        return dao.selectByPrimaryKey(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> get(Po record) {
        logger.debug("get [record={}]", JSON.toJSON(record));
        return dao.select(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Po> getAll() {
        return dao.select(null);
    }

    /**
     * @param example
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example example) {
        logger.debug("exists [example={}]", JSON.toJSON(example));
        List<Po> datas = dao.selectByExample(example);
        return datas != null && datas.size() > 0;
    }

    /**
     * 根据条件查询零条及多条数据
     *
     * @param example 查询条件参数
     * @return 记录列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<Po> getByExample(Example example) {
        logger.debug("getByExample [example={}]", JSON.toJSON(example));
        return dao.selectByExample(example);
    }

    /**
     * 根据条件查询一条数据
     *
     * @param example 查询条件参数
     */
    @Override
    @Transactional(readOnly = true)
    public Po getOneByExample(Example example) {
        logger.debug("getOneByExample [example={}]", JSON.toJSON(example));
        List<Po> datas = dao.selectByExample(example);
        if (datas != null && datas.size() > 0) {
            return datas.get(0);
        }
        return null;
    }

}
