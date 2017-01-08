/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.mapper;


import com.xqsight.common.dao.Dao;

import com.xqsight.system.model.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>菜单信息表实现类service</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-01-07 11:57:32
 * @author wangganggang
*/
public interface SysMenuMapper extends Dao<SysMenu,Long>{

    @Select("select menu_id from sys_menu_role where id = #{userId, jdbcType=NUMERIC}")
    List<String> queryMenuByUser(Long userId);
}