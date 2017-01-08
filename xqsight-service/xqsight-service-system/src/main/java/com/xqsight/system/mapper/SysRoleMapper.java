/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.mapper;


import com.xqsight.common.dao.Dao;

import com.xqsight.system.model.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>角色信息表实现类service</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-01-07 11:58:03
 * @author wangganggang
*/
public interface SysRoleMapper extends Dao<SysRole,Long>{

    @Select("select role_id from sys_user_role where id = #{userId, jdbcType=NUMERIC}")
    List<String> queryRoleByUser(long userId);
}