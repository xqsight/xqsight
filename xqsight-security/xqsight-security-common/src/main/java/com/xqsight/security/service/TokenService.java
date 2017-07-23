package com.xqsight.security.service;

import com.xqsight.common.model.shiro.UserToken;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月22日 下午12:57
 */
public interface TokenService {

    UserToken queryByUserId(Long userId);

    UserToken queryByToken(String token);

    void save(UserToken token);

    void update(UserToken token);

    Map<String, Object> createToken(Long userId);
}
