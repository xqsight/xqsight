package com.tangchao.api.controller;

import com.tangchao.house.service.SmsService;
import com.xqsight.security.annontation.AuthIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午9:55
 */
@RestController
@RequestMapping("/api")
public class ValidateCodeController {

    @Autowired
    private SmsService smsService;

    @AuthIgnore
    @RequestMapping(value = "send/code/{telePhone}", method = RequestMethod.GET)
    public Object sendValidate(@PathVariable String telePhone) {
        return smsService.sendValidCode(telePhone);
    }
}
