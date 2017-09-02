package com.xqsight.common.base.dao;

/**
 * @param <T>
 * @author wangganggang
 * @Date 2017/3/23
 * <p>
 * 基本增删改查(CRUD)数据访问接口
 */
public interface ICrudDao<T> extends
        IInsertDao<T>,
        IDeleteDao<T>,
        IUpdateDao<T>,
        ISelectDao<T> {
}
