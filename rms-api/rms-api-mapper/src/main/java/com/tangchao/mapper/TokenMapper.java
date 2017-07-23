package com.tangchao.mapper;

import com.tangchao.model.TokenEntity;

/**
 * @author wangganggang
 * @date 2017年07月21日 16:39
 */
public interface TokenMapper {

    TokenEntity queryByUserId(String userId);

    TokenEntity queryByToken(String token);

    void save(TokenEntity tokenEntity);

    void update(TokenEntity tokenEntity);
}
