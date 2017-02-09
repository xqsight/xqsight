package com.xqsight.authc.appcontroller;

import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.support.LoginSupport;
import com.xqsight.common.model.UserBaseModel;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.web.WebUtils;
import com.xqsight.sso.authc.SSOUsernamePasswordToken;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.enums.UserType;
import com.xqsight.sso.exceptions.CustomAuthcException;
import com.xqsight.sso.shiro.constants.WebConstants;
import com.xqsight.sso.subject.SSOSubject;
import com.xqsight.sso.utils.PasswordHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @version SignUpController.java, v 0.1 2015年9月29日 下午11:20:53
 */
@Controller
@RequestMapping("/authc")
public class SignUpController extends ValidateCodeController {

    private final static Logger logger = LogManager.getLogger(SignUpController.class);

    private String defaulFinishUrl = "";

    @Autowired
    private UserAuthcService sysUserAuthcService;

    @RequestMapping("/signup")
    public Object signUp(HttpServletRequest request) {
        String gotoUrl = request.getParameter(WebConstants.GO_TO);
        try {
            // 判断验证码
            checkCode(request);

            doSignUp(request);
        } catch (AuthenticationException e) {
            logger.error(e);
            request.setAttribute(Constants.KEY_STATUS, Constants.FAILURE);
            request.setAttribute(Constants.KEY_MESSAGE, e.getMessage());
            return null;
        }
        return StringUtils.hasLength(gotoUrl) ? gotoUrl : defaulFinishUrl;
    }

    @ResponseBody
    @RequestMapping("/ajaxSignup")
    public Object ajaxSignUp(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 判断验证码
            checkCode(request);

            SSOUsernamePasswordToken token = doSignUp(request);
            doAfterSignUp(token);
        } catch (AuthenticationException e) {
            logger.error(e);
            if (e instanceof CustomAuthcException) {
                map.put(Constants.KEY_MESSAGE, e.getMessage());
            } else {
                map.put(Constants.KEY_STATUS, Constants.SUCCESS);
                map.put(Constants.KEY_MESSAGE, "注册成功，登陆发生了异常");
            }
            return WebUtils.getResponseBody(request, map);
        }
        map.put(Constants.KEY_STATUS, Constants.SUCCESS);

        return WebUtils.getResponseBody(request, map);
    }

    /**
     * 操作个人用户注册流程
     *
     * @param request
     * @return
     * @throws CustomAuthcException
     */
    public SSOUsernamePasswordToken doSignUp(HttpServletRequest request) throws CustomAuthcException {
        String loginId = request.getParameter(WebConstants.LOGIN_ID);
        String password = request.getParameter(WebConstants.PASSWORD);
        String userName = request.getParameter(WebConstants.USERNAME);
        String departmentCode = request.getParameter(WebConstants.DEPARTMENTCODE);
        LoginTypeEnum loginType = LoginSupport.judgeLoginType(loginId);
        UserBaseModel user = null;
        switch (loginType) {
            case CELLPHONE:
            case EMAIL:
            case USERID:
                break;
            default:
                logger.error("Not Support LoginType:{}", loginType.toString());
                throw new IllegalArgumentException("Not Support LoginType");
        }

        user = sysUserAuthcService.findByLoginId(loginId);

        if (user == null) {
            UserBaseModel model = new UserBaseModel();
            model.setLoginId(loginId);
            model.setUserName(userName);
            model.setDepartmentCode(departmentCode);
            model.setLocked(Constants.YES);
            model.setLoginType(loginType.value());
            model.setPassword(password);
            PasswordHelper.encryptPassword(model);
            sysUserAuthcService.saveUser(model);
        } else {
            throw new CustomAuthcException(-1, "当前用户已经存在");
        }
        return new SSOUsernamePasswordToken(loginId, password, false, UserType.PERSON);
    }

    /**
     * 登陆
     *
     * @param token
     * @throws AuthenticationException
     */
    private void doAfterSignUp(SSOUsernamePasswordToken token) throws AuthenticationException {
        SSOSubject currentUser = SSOUtils.getCurrentUserSubject();
        if (!currentUser.isAuthenticated()) {
            currentUser.login(token);
        }
    }
}
