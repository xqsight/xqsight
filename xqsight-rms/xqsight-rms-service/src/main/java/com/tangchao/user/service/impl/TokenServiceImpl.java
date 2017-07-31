package com.tangchao.user.service.impl;

import com.tangchao.user.mapper.TokenMapper;
import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.tangchao.constans.RmsContants;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.TokenService;
import com.xqsight.security.supoort.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author wangganggang
 * @date 2017年07月24日 13:55
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public UserToken queryByUserId(Long userId) {
        UserToken userToken = tokenMapper.selectById(userId);
        return userToken;
    }

    @Override
    public UserToken queryByToken(String token) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().propertyType(PropertyType.S)
                .matchTye(MatchType.EQ).add("token",token).end();
        List<UserToken> userTokens = tokenMapper.search(new Criterion(propertyFilters));
        if (userTokens.size() > 0){
            return userTokens.get(0);
        }
        return null;
    }

    @Override
    public UserToken createTokenAndSave(Long userId) {
        UserToken userToken = queryByUserId(userId);
        UserToken saveToken = new UserToken();
        saveToken.setUserId(userId);
        saveToken.setToken(TokenGenerator.generateValue());
        saveToken.setExpireTime(LocalDate.now().plusDays(RmsContants.TOKEN_EXPIRE_DAY));
        if(userToken == null){
            tokenMapper.insert(saveToken);
        }else{
            tokenMapper.updateById(saveToken);
        }
        return saveToken;
    }
}
