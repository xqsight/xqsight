package com.xqsight.common.base.service;

/**
 * @param <T>
 * @author wangganggang
 * @Date 2017/3/23
 * <p>
 * 基本增删改查(CRUD)数据访问服务接口
 */
public interface ICrudService<T, Example> extends
        IAddService<T>,
        IRemoveService<T, Example>,
        IEditService<T, Example>,
        IGetService<T, Example> {
}
