package com.tangchao.user.mapper;

import com.xqsight.common.model.shiro.UserToken;

/**
 * @author wangganggang
 * @date 2017年07月21日 16:39
 */
public interface TokenMapper {

    UserToken queryByUserId(String userId);

    UserToken queryByToken(String token);

    void save(UserToken tokenEntity);

    void update(UserToken tokenEntity);
}
