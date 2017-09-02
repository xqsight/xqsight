package com.xqsight.common.base.service;

import com.alibaba.fastjson.JSON;
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
public abstract class AbstractCrudService<Dao extends ICrudDao<Po>, Po, Example>
        extends AbstractGetService<Dao, Po, Example> implements ICrudService<Po, Example> {

    @Override
    @Transactional
    public int save(Po record) {
        logger.debug("save [record={}]", JSON.toJSON(record));
        return this.dao.insert(record);
    }

    @Override
    @Transactional
    public int saveSelective(Po record) {
        logger.debug("saveSelective [record={}]", JSON.toJSON(record));
        return this.dao.insertSelective(record);
    }

    @Override
    @Transactional
    public int batchSave(List<Po> records) {
        logger.debug("batchSave [records={}]", JSON.toJSON(records));
        return this.dao.insertList(records);
    }

    @Override
    @Transactional
    public int edit(Po record) {
        logger.debug("edit [record={}]", JSON.toJSON(record));
        return this.dao.updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int editSelective(Po record) {
        logger.debug("editSelective [record={}]", JSON.toJSON(record));
        return this.dao.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int remove(Po record) {
        logger.debug("remove [record={}]", JSON.toJSON(record));
        return this.dao.delete(record);
    }

    @Override
    @Transactional
    public int removeById(Po record) {
        logger.debug("removeById [record={}]", JSON.toJSON(record));
        return this.dao.deleteByPrimaryKey(record);
    }

    /**
     * @param example
     * @return
     */
    @Override
    @Transactional
    public int removeByExample(Example example) {
        logger.debug("removeByExample [example={}]", JSON.toJSON(example));
        return this.dao.deleteByExample(example);
    }

    /**
     * 根据条件更新数据
     *
     * @param record
     * @param example
     * @return 影响的记录数
     */
    @Override
    @Transactional
    public int editByExample(Po record, Example example) {
        logger.debug("editByExample [record={}],[example={}]", JSON.toJSON(record),JSON.toJSON(example));
        return this.editByExample(record,example);
    }

    /**
     * @param records
     * @return 影响的记录数
     */
    @Override
    @Transactional
    public int batchEdit(List<Po> records) {
        logger.debug("batchEdit [records={}]", JSON.toJSON(records));
        int ret = 0;
        for (Po po : records) {
            ret += this.dao.updateByPrimaryKeySelective(po);
        }
        return ret;
    }
}
