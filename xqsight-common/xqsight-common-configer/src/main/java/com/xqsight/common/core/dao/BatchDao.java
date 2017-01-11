package com.xqsight.common.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BatchDao<T, PK extends Serializable> extends Dao<T, PK> {
    /**
     * 保存新增的对象.
     */
    void batchSave(final List<T> entityList);

    /**
     * 保存修改的对象.
     */
    void batchUpdate(final List<T> entityList);

    /**
     * 按id删除对象.
     */
    void batchDelete(final List<PK> entityList);

    /**
     * map条件删除
     * 调用procedure
     */
    void batchDelete(Map<String, Object> conMap);

    /**
     * 带where条件的删除
     * 调用procedure
     */
    void batchWhereDel(String whereSql);

    /**
     * 批处理过程
     */
    void batchProcessor();

    /**
     * 批启动预处理
     */
    void preBatchProcessor(Map<String, Object> map);
}
