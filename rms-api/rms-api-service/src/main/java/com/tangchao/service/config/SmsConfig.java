package com.tangchao.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:50
 */
@Data
@Configuration
public class SmsConfig {

    @Value("${sms.url}")
    private String smsUrl;

    @Value("${sms.account}")
    private String smsAccount;

    @Value("${sms.password}")
    private String smsPassword;

    @Value("${sms.sign}")
    private String smsSign;

}
