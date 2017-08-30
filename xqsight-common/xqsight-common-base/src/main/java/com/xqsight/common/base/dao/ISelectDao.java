package com.xqsight.common.base.dao;

import com.xqsight.common.model.BaseModel;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByConditionRowBoundsMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper;

/**
 * @param <T>
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface ISelectDao<T extends BaseModel> extends BaseSelectMapper<T>,
        SelectByConditionMapper<T>,
        SelectCountByConditionMapper<T>,
        SelectByExampleMapper<T>,
        SelectCountByExampleMapper<T>,
        SelectByConditionRowBoundsMapper<T>,
        SelectByExampleRowBoundsMapper<T>,
        SelectRowBoundsMapper<T> {
}
