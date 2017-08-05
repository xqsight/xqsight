package com.tangchao.api.controller;

import com.tangchao.user.model.AppUser;
import com.tangchao.user.service.AppUserService;
import com.tangchao.user.service.SmsService;
import com.xqsight.common.exception.SmsSendException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.common.utils.StringUtils;
import com.xqsight.security.annontation.AuthIgnore;
import com.xqsight.security.annontation.CurrentUserId;
import com.xqsight.security.service.TokenService;
import com.xqsight.security.service.UserAuthcService;
import com.xqsight.security.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private AppUserService appUserService;

    @AuthIgnore
    @RequestMapping(value = "login/code/", method = RequestMethod.POST)
    public Object loginbyCode(String telephone, String validateCode) {
        boolean isValidate = smsService.verifyCode(telephone, validateCode);
        if (!isValidate) {
            throw new SmsSendException(ErrorMessageConstants.ERROR_50002, "验证码错误");
        }
        BaseUserModel baseUserModel = userAuthcService.queryUserByLoginId(telephone);
        if (baseUserModel != null && StringUtils.equals(baseUserModel.getLoginId(), telephone)) {
            UserToken userToken = tokenService.createTokenAndSave(baseUserModel.getId());
            return BaseResult.success().data(userToken);
        }
        return BaseResult.failure().message("当前用户不存在");
    }

    @AuthIgnore
    @RequestMapping(value = "login/pwd/", method = RequestMethod.POST)
    public Object loginByPwd(String telephone, String password) {
        BaseUserModel baseUserModel = userAuthcService.queryUserByLoginId(telephone);
        boolean isRight = PasswordHelper.checkPassword(baseUserModel,password);
        if (isRight) {
            UserToken userToken = tokenService.createTokenAndSave(baseUserModel.getId());
            return BaseResult.success().data(userToken);
        }
        return BaseResult.failure().message("用户名或密码不对");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Object logout(@CurrentUserId Long currentId) {
        userAuthcService.logout(currentId);
        return BaseResult.success();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public Object register(AppUser appUser, String validateCode) {
        boolean isValidate = smsService.verifyCode(appUser.getLoginId(), validateCode);
        if (!isValidate) {
            throw new SmsSendException(ErrorMessageConstants.ERROR_50002, "验证码错误");
        }
        appUser.setCellPhone(appUser.getLoginId());
        PasswordHelper.encryptPassword(appUser);
        appUserService.add(appUser);
       return BaseResult.success();
    }

    @RequestMapping(value = "resetpwd", method = RequestMethod.GET)
    public Object resetPwd(AppUser appUser, String validateCode) {
        boolean isValidate = smsService.verifyCode(appUser.getLoginId(), validateCode);
        if (!isValidate) {
            throw new SmsSendException(ErrorMessageConstants.ERROR_50002, "验证码错误");
        }
        appUser.setCellPhone(appUser.getLoginId());
        PasswordHelper.encryptPassword(appUser);
        appUserService.editById(appUser);
        return BaseResult.success();
    }

}
