package com.tangchao.service.impl;

import com.alibaba.fastjson.JSON;
import com.tangchao.service.SmsService;
import com.tangchao.service.config.SmsConfig;
import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.exception.SmsSendException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:49
 */
@Service
public class SmsServiceImpl implements SmsService {

    protected Logger logger = LogManager.getLogger(SmsServiceImpl.class);

    @Autowired
    private SmsConfig smsConfig;

    private OkHttpClient httpClient = new OkHttpClient();

    @Override
    public String sendValidCode(String telPhone) {

        String sendContent = converParams(telPhone);

        logger.debug("send params:{}", sendContent);

        Request request = new Request.Builder().url(smsConfig.getSmsUrl()).
                post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), sendContent)).build();

        String retMsg = null;
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                retMsg = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new SmsSendException(ErrorMessageConstants.ERROR_30001,"短信验证码发送失败");
        }
        return retMsg;
    }

    @Override
    public boolean validate(String telPhone, String validcode) {

        return false;
    }


    private String converParams(String telPhone) {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account", smsConfig.getSmsAccount());
        paramsMap.put("password", md5(smsConfig.getSmsPassword()));
        paramsMap.put("msgid", UUID.randomUUID().toString().replace("-", ""));
        paramsMap.put("phones", telPhone);
        paramsMap.put("content", "");
        paramsMap.put("sign", smsConfig.getSmsSign());
        return JSON.toJSON(paramsMap).toString();
    }

    private String md5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ParamsException(ErrorMessageConstants.ERROR_10000,"密码加密失败");
        }
        byte bytes[] = md.digest(password.getBytes());
        for (int i = 0; i < bytes.length; i++) {
            String str = Integer.toHexString(bytes[i] & 0xFF);
            if (str.length() == 1) {
                str += "F";
            }
            result += str;
        }
        return result;
    }
}
