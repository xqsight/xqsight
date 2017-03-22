package com.xqsight.common.core.dao;

import java.io.Serializable;

/**
 * 基本增删改查(CRUD)数据访问接口
 *
 * @param <T> Po
 */
public interface ICrudDao<T, PK extends Serializable> extends
        IInsertDao<T>,
        IDeleteDao<PK>,
        IUpdateDao<T>,
        ISelectDao<T,PK> {
}
