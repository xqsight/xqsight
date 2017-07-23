package com.tangchao.security.service;

/**
 * @author wangganggang
 * @date 2017年07月21日 下午11:47
 */
public interface SmsService {

    String sendValidCode(String telPhone);

    boolean validate(String telPhone,String validcode);
}
