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

    @Value("${sms.url:#{null}}")
    private String smsUrl;

    @Value("${sms.account:#{null}}")
    private String smsAccount;

    @Value("${sms.password:#{null}}")
    private String smsPassword;

    @Value("${sms.sign:#{null}}")
    private String smsSign;

}
