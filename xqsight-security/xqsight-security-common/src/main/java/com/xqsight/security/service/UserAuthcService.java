package com.xqsight.security.service;

import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;

import java.util.Set;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午8:54
 */
public interface UserAuthcService {

    BaseUserModel queryUserById(Long userId);

    BaseUserModel queryUserByLoginId(String loginId);

    /**
     * 根据用户id查找其角色
     * @param userId
     * @return
     */
    Set<String> findRoles(Long userId);

    /**
     * 根据用户id查找其权限
     * @param userId
     * @return
     */
    Set<String> findPermissions(Long userId);


    UserToken queryByToken(String token);

    void logout(Long userId);
}
