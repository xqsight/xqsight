package com.xqsight.common.base.dao;

import com.xqsight.common.model.BaseModel;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

import java.util.List;

/**
 * @param <T>
 * @author wangganggang
 * @Date 2017/3/23
 */
public interface IUpdateDao<T extends BaseModel> extends BaseUpdateMapper<T>,
        UpdateByConditionMapper<T>,
        UpdateByConditionSelectiveMapper<T>,
        UpdateByExampleMapper<T>,
        UpdateByExampleSelectiveMapper<T> {

}
