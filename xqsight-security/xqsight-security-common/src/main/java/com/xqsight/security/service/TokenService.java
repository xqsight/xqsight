package com.xqsight.security.service;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.common.model.shiro.UserToken;

import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月22日 下午12:57
 */
public interface TokenService extends ICrudService<UserToken, Long> {

    UserToken queryByUserId(Long userId);

    UserToken queryByToken(String token);

    /**
     * 创建token 并 保存
     * @param userId
     * @return 过期时间和token
     */
    Map<String, Object> createTokenAndSave(Long userId);
}
