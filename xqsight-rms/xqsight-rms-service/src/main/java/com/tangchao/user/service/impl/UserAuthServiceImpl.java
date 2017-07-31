package com.tangchao.user.service.impl;

import com.tangchao.user.mapper.AppUserMapper;
import com.tangchao.user.model.AppUser;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.TokenService;
import com.xqsight.security.service.UserAuthcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author wangganggang
 * @date 2017年07月24日 13:54
 */
@Service
public class  UserAuthServiceImpl implements UserAuthcService {

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public BaseUserModel queryUserById(Long userId) {
        AppUser appUser = appUserMapper.selectById(userId);
        return appUser;
    }

    @Override
    public BaseUserModel queryUserByLoginId(String loginId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().propertyType(PropertyType.S)
                .matchTye(MatchType.EQ).add("login_id",loginId).end();
        List<AppUser> appUsers = appUserMapper.search(new Criterion(propertyFilters));
        if(appUsers.size() > 0){
            return appUsers.get(0);
        }
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
        return tokenService.queryByToken(token);
    }

    @Override
    public void logout(Long userId) {
        tokenService.createTokenAndSave(userId);
    }
}
