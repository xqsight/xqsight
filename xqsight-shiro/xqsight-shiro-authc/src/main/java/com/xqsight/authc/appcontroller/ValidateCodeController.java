package com.xqsight.authc.appcontroller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.xqsight.common.web.WebUtils;
import com.xqsight.sso.exceptions.CustomAuthcException;
import com.xqsight.sso.shiro.constants.WebConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by user on 2016/5/20.
 */
public class ValidateCodeController {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private Producer captchaProducer;

    @RequestMapping("/getCode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 获取验证码
        String capText = captchaProducer.createText();
        logger.debug("[captcha]获取的验证码为：{}", capText);
        // 存入session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        BufferedImage bi = captchaProducer.createImage(capText);

        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
            out.close();
        } catch (IOException e) {
            logger.info("返回验证码出错：{}", e.getMessage());
        }
    }

    /**
     * 验证码判断处理
     *
     * @param request
     * @throws CustomAuthcException
     */
    protected void checkCode(HttpServletRequest request) throws CustomAuthcException {
        //如果是移动端，不验证
        if(WebUtils.isMobile(request))
            return;

        HttpSession session = request.getSession();
        String checkCode = request.getParameter(WebConstants.VALIDATA_CODE);
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("get validataCode:{} && session validataCode:{}", checkCode, code);
        // 判断是否相等
        if (StringUtils.isEmpty(checkCode) || StringUtils.isEmpty(code) || !checkCode.equals(code)) {
            throw new CustomAuthcException(-1,"验证码错误");
        }
    }

}
