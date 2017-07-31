package com.tangchao.user.service.impl;

import com.tangchao.constans.RmsContants;
import com.tangchao.constans.TemplateConstans;
import com.tangchao.user.service.SmsService;
import com.tangchao.service.config.SmsConfig;
import com.tangchao.utils.GenerateCode;
import com.xqsight.common.exception.SmsSendException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.utils.StringUtils;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.data.ehcache.support.EhcacheKey;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:49
 */
@Service
public class SmsServiceImpl implements SmsService {

    protected Logger logger = LogManager.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private CacheTemplate cacheTemplate;

    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    public BaseResult sendValidCode(String telPhone) {
        Integer requestTimes = (Integer) cacheTemplate.get(EhcacheKey.getSysConfigKey(RmsContants.VALIDATE_CODE_TIMES + telPhone),0);

        if (requestTimes >= RmsContants.VALIDATE_CODE_REQUEST_MAX_TIMES) {
            throw new SmsSendException(ErrorMessageConstants.ERROR_30002, "短信验证码连续发送次数最多不能超过:" + RmsContants.VALIDATE_CODE_REQUEST_MAX_TIMES +" 次");
        }
        String code = GenerateCode.generateCode();
        String content = String.format(TemplateConstans.VALIDATE_CODE_TEMPLATE, code);
        String sendContent = smsConfig.converParams(telPhone, content);

        logger.debug("send validate code params:{}", sendContent);

        Request request = new Request.Builder().url(smsConfig.getSmsUrl()).
                post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), sendContent)).build();

        String retMsg = null;
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                requestTimes++;
                cacheTemplate.put(EhcacheKey.getSysConfigKey(RmsContants.VALIDATE_CODE_TIMES + telPhone), requestTimes);
                retMsg = response.body().string();
                logger.debug("send validate code response :{}", retMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new SmsSendException(ErrorMessageConstants.ERROR_30001, "短信验证码发送失败");
        }
        /*缓存存放10分钟*/
        cacheTemplate.put(EhcacheKey.getSysConfigKey(RmsContants.VALIDATE_CODE + telPhone), code,10*60);
        return BaseResult.success();
    }

    @Override
    public boolean verifyCode(String telPhone, String validcode) {
        Assert.hasLength(validcode, "验证码不能为空！");
        String code = (String) cacheTemplate.get(EhcacheKey.getSysConfigKey(telPhone));
        if (StringUtils.equals(code, validcode)) {
            cacheTemplate.put(EhcacheKey.getSysConfigKey(RmsContants.VALIDATE_CODE_TIMES + telPhone), 0);
            return true;
        }
        return false;
    }


}
