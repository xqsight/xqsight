package com.tangchao.security.service.impl;

import com.tangchao.security.model.TokenEntity;
import com.tangchao.security.service.TokenService;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月21日 16:35
 */
public class TokenServiceImpl implements TokenService {
    @Override
    public TokenEntity queryByUserId(Long userId) {
        return null;
    }

    @Override
    public TokenEntity queryByToken(String token) {
        return null;
    }

    @Override
    public void save(TokenEntity token) {

    }

    @Override
    public void update(TokenEntity token) {

    }

    @Override
    public Map<String, Object> createToken(String userId) {
        return null;
    }
}
