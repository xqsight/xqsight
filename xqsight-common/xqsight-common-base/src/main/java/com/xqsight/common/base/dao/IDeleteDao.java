package com.xqsight.common.base.dao;

import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;

/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 */
public interface IDeleteDao<T> extends BaseDeleteMapper<T>,
        DeleteByConditionMapper<T>,
        DeleteByExampleMapper<T> {


}

