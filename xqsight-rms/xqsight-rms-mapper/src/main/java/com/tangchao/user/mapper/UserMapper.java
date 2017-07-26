package com.tangchao.user.mapper;

import com.xqsight.common.model.shiro.BaseUserModel;

import java.util.Set;

/**
 * @author wangganggang
 * @date 2017年07月25日 13:26
 */
interface UserMapper {
    BaseUserModel queryUserById(Long userId);

    BaseUserModel queryUserByLoginId(String loginId);

    Set<String> findRoles(Long userId);

    Set<String> findPermissions(Long userId);

}
