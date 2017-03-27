package com.xqsight.common.base.dao;

import java.io.Serializable;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * 基本增删改查(CRUD)数据访问接口
 *
 * @param <T>
 * @param <PK>
 */
public interface ICrudDao<T, PK extends Serializable> extends
        IInsertDao<T>,
        IDeleteDao<PK>,
        IUpdateDao<T>,
        ISelectDao<T,PK> {
}
