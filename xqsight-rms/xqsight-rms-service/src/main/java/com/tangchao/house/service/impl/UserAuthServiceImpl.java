package com.tangchao.house.service.impl;

import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.UserAuthcService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author wangganggang
 * @date 2017年07月24日 13:54
 */
@Service
public class UserAuthServiceImpl implements UserAuthcService {
    @Override
    public BaseUserModel queryUserById(Long userId) {
        return null;
    }

    @Override
    public BaseUserModel queryUserByLoginId(String loginId) {
        return null;
    }

    @Override
    public Set<String> findRoles(Long userId) {
        return null;
    }

    @Override
    public Set<String> findPermissions(Long userId) {
        return null;
    }

    @Override
    public UserToken queryByToken(String token) {
        return null;
    }

    @Override
    public void logout(Long userId) {

    }
}
