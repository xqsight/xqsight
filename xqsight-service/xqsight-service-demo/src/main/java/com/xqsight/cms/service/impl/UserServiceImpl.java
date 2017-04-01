package com.xqsight.cms.service.impl;

import com.xqsight.common.model.UserBaseModel;
import com.xqsight.sso.authc.service.UserAuthcService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author wangganggang
 * @date 2017/04/01
 */
@Service
public class UserServiceImpl implements UserAuthcService {

    @Override
    public UserBaseModel findByLoginId(String loginId) {
        return null;
    }

    @Override
    public Set<String> findRoles(long id) {
        return null;
    }

    @Override
    public Set<String> findPermissions(long id) {
        return null;
    }

    @Override
    public void saveUser(UserBaseModel userBaseModel) {

    }
}
