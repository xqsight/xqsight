package com.tangchao.utils;

import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午9:25
 */
public class RmsUtils {

    public static String md5(String password) {
        String result = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
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
