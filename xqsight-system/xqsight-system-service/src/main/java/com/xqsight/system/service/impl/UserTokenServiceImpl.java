package com.xqsight.system.service.impl;

import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月26日 9:44
 */
@Service
public class UserTokenServiceImpl implements TokenService {
    @Override
    public UserToken queryByUserId(Long userId) {
        return null;
    }

    @Override
    public UserToken queryByToken(String token) {
        return null;
    }

    @Override
    public UserToken createTokenAndSave(Long userId) {
        return null;
    }
}
