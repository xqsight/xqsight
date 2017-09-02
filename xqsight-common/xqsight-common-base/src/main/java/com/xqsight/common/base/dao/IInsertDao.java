package com.xqsight.common.base.dao;

import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * @author wangganggang
 *
 * @Date 2017/3/23
 */
public interface IInsertDao<T> extends BaseInsertMapper<T> ,InsertListMapper {

}
