package com.tangchao.house.service.impl;

import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月24日 13:55
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public UserToken queryByUserId(Long userId) {
        return null;
    }

    @Override
    public UserToken queryByToken(String token) {
        return null;
    }

    @Override
    public Map<String, Object> createTokenAndSave(Long userId) {
        return null;
    }
}
