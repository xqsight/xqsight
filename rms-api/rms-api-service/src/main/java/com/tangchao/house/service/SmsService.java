package com.tangchao.house.service;

import com.xqsight.common.model.BaseResult;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:47
 */
public interface SmsService {

    BaseResult sendValidCode(String telPhone);

    boolean validate(String telPhone,String validcode);
}
