package com.tangchao.security.service;

import com.tangchao.security.model.TokenEntity;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月21日 16:33
 */
public interface TokenService {

    TokenEntity queryByUserId(Long userId);

    TokenEntity queryByToken(String token);

    void save(TokenEntity token);

    void update(TokenEntity token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return 返回token相关信息
     */
    Map<String, Object> createToken(String userId);
}
