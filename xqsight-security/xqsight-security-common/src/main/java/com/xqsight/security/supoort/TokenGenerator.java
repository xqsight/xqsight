package com.xqsight.security.supoort;

import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午9:54
 */
public class TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParamsException(ErrorMessageConstants.ERROR_10000, "生成Token失败");
        }
    }
}
