package com.xqsight.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
@Data
@Component
public class BaseInfoProperties {

    @Value("${sign.user:}")
    private String SignUser;


    private String appId="1614";

    private String loginUrl="https://mwf.ehousechina.com:8443/SecretVerify.ashx";

    private String authUrl="https://mwf.ehousechina.com/WFM/MobileAuthNew.aspx";

    private String signUrl="https://mwf.ehousechina.com/WFM/HR/CheckIn.aspx";
}
