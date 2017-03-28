package com.xqsight.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wangganggang
 * @date 2017/03/28
 */
public class PasswordHelp {

    protected static Logger logger = LogManager.getLogger(PasswordHelp.class);

    public static String getMd5Str(String pwd) {
        logger.debug("MD5pwd={}", pwd);
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte[] b = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            logger.debug("MD5--getMD5Str()--NoSuchAlgorithmException", e.getMessage());
        }
        return buf == null ? "" : buf.toString();
    }

}
