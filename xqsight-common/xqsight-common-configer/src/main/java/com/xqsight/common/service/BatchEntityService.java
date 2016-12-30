package com.xqsight.common.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xqsight.common.dao.BatchDao;
import com.xqsight.common.model.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 * 带有批量操作的Service
 */
@Transactional
public abstract class BatchEntityService<T extends Model, PK extends Serializable> extends DefaultEntityService<T, PK> {
    /**
     * 在子类实现的回调函数,为EntityManager提供默认CRUD操作所需的DAO.
     */
    protected abstract BatchDao<T, PK> getDao();

    /**
     * 保存新增的对象.
     */
    @Transactional
    public void batchSave(final List<T> entityList) {
        getDao().batchSave(entityList);
    }

    /**
     * 保存修改的对象.
     */
    @Transactional
    public void batchUpdate(final List<T> entityList) {
        getDao().batchUpdate(entityList);
    }

    /**
     * 按id删除对象.
     */
    @Transactional
    public void batchDelete(final List<PK> entityList) {
        int maxSize = 500;

        double d = ((double) entityList.size()) / maxSize;
        for (int splitTimes = 0; splitTimes < (int) Math.ceil(d); splitTimes++) {
            List<PK> list = new ArrayList<PK>();
            for (int i = splitTimes * maxSize; i < maxSize * (splitTimes + 1) && i < entityList.size(); i++) {
                if (entityList.get(i) != null) {
                    list.add(entityList.get(i));
                }
            }
            getDao().batchDelete(list);
        }
    }

    /**
     * map条件删除
     * 调用procedure
     */
    @Transactional
    public void batchDelete(Map<String, Object> conMap) {
        if (conMap != null && conMap.size() > 0) {
            getDao().batchDelete(conMap);
        }
    }

    /**
     * 带where条件的删除
     * 调用procedure
     */
    @Transactional
    public void batchWhereDel(String whereSql) {
        if (StringUtils.isNotBlank(whereSql)) {
            whereSql = " and " + whereSql;
        }
        getDao().batchWhereDel(whereSql);
    }

    /**
     * 调用procedure.
     */
    @Transactional
    public void batchProcessor() {
        getDao().batchProcessor();
    }

    /**
     * 批预处理调用procedure.
     */
    @Transactional
    public void preBatchProcessor(Map<String, Object> paras) {
        getDao().preBatchProcessor(paras);
    }
}
