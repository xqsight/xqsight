package com.xqsight.common.core.service;

import java.io.Serializable;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * 基本增删改查(CRUD)数据访问服务接口
 *
 * @param <T>
 * @param <PK>
 */
public interface ICrudService<T, PK extends Serializable> extends
        IAddService<T>,
        IRemoveService<PK>,
        IEditService<T>,
        IGetService<T, PK> {
}
