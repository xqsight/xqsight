package com.tangchao.utils;

import java.util.Random;

/**
 * @author wangganggang
 * @date 2017年07月26日 下午9:33
 */
public class GenerateCode {

    public static String generateCode() {

        StringBuilder sbCode = new StringBuilder();

        char[] ch = "0123456789".toCharArray();

        Random r = new Random();
        int len = ch.length;
        for (int i = 0; i < 4; ++i) {
            int index = r.nextInt(len);
            sbCode.append(ch[index]);
        }
        return sbCode.toString();
    }
}
