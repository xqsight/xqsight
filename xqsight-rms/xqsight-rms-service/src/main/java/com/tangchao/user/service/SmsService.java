package com.tangchao.user.service;

import com.xqsight.common.model.BaseResult;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:47
 */
public interface SmsService {

    BaseResult sendValidCode(String telPhone);

    boolean verifyCode(String telPhone,String validcode);
}
