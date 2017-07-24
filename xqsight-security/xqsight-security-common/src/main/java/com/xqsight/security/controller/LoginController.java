package com.xqsight.security.controller;

import com.google.code.kaptcha.Producer;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.security.annontation.CurrentUserId;
import com.xqsight.security.service.TokenService;
import com.xqsight.security.service.UserAuthcService;
import com.xqsight.security.utils.SSOUtils;
import org.apache.poi.util.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2017年07月22日 下午4:41
 */
@RestController
@RequestMapping("／")
public class LoginController {

    @Autowired
    private Producer producer;
    @Autowired
    private UserAuthcService userAuthcService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        SSOUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(String loginId, String password, String captcha)throws IOException {
        String kaptcha = SSOUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return new BaseResult(Constants.FAILURE,"验证码不正确");
        }

        //用户信息
        BaseUserModel user = userAuthcService.queryUserByLoginId(loginId);

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return new BaseResult(Constants.FAILURE,"账号或密码不正确");
        }

        //账号锁定
        if(user.isUserLocked()){
            return new BaseResult(Constants.FAILURE,"账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Map tokenMap  = tokenService.createTokenAndSave(user.getId());
        return new BaseResult(tokenMap);
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Object logout(@CurrentUserId Long userId) {
        userAuthcService.logout(userId);
        return new BaseResult();
    }
}
