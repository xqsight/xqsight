package com.tangchao.service.config;

import com.alibaba.fastjson.JSON;
import com.tangchao.utils.RmsUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:50
 */
@Data
@Configuration
public class SmsConfig {

    @Value("${sms.url:#{null}}")
    private String smsUrl;

    @Value("${sms.account:#{null}}")
    private String smsAccount;

    @Value("${sms.password:#{null}}")
    private String smsPassword;

    @Value("${sms.sign:#{null}}")
    private String smsSign;

    public String converParams(String telPhone,String content) {
        Map<String, String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("account", this.getSmsAccount());
        paramsMap.put("password", RmsUtils.md5(this.getSmsPassword()));
        paramsMap.put("msgid", UUID.randomUUID().toString().replace("-", ""));
        paramsMap.put("phones", telPhone);
        paramsMap.put("content", content);
        paramsMap.put("sign", this.getSmsSign());
        return JSON.toJSON(paramsMap).toString();
    }
}
