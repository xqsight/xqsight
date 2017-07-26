package com.tangchao.api.controller;

import com.tangchao.house.service.SmsService;
import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.utils.StringUtils;
import com.xqsight.security.annontation.AuthIgnore;
import com.xqsight.security.annontation.CurrentUserId;
import com.xqsight.security.service.TokenService;
import com.xqsight.security.service.UserAuthcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:43
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserAuthcService userAuthcService;

    @Autowired
    private TokenService tokenService;

    @AuthIgnore
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(String telephone, String validateCode) {
        boolean isValidate = smsService.verifyCode(telephone, validateCode);
        if (!isValidate) {
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40004, "验证码错误");
        }
        Map token = new HashMap();
        BaseUserModel baseUserModel = userAuthcService.queryUserByLoginId(telephone);
        if (baseUserModel != null && StringUtils.equals(baseUserModel.getLoginId(), telephone)) {
            token = tokenService.createTokenAndSave(baseUserModel.getId());
        }
        return BaseResult.success().data(token);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Object logout(@CurrentUserId Long currentId) {
        userAuthcService.logout(currentId);
        return BaseResult.success();
    }

}
