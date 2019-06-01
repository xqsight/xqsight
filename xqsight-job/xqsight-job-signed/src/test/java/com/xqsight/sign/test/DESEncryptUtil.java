package com.xqsight.sign.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class DESEncryptUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DESEncryptUtil.class);

    //加密
    public static String encrypt(String data,String key){
        if(StringUtils.isBlank(data)||StringUtils.isBlank(key)){
            LOGGER.info("参数和密钥不允许为空");
            return null;
        }
        String strs = null;
        try {
            byte[] bytes = encryptOrDecrypt(Cipher.ENCRYPT_MODE,data.getBytes(),key.getBytes());
            // base64编码字节
            strs = new String(new Base64().encode(bytes),"utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("加密失败，errormsg={}",e.getMessage());
        }
        return strs;
    }

    //解密
    public static String decrypt(String data,String key){
        if(StringUtils.isBlank(data)||StringUtils.isBlank(key)){
            LOGGER.info("参数和密钥不允许为空");
            return null;
        }
        String strs = null;
        try {
            byte[] src = new Base64().decode(data);
            byte[] bytes = encryptOrDecrypt(Cipher.DECRYPT_MODE,src,key.getBytes());
            strs = new String(bytes,"utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("解密失败，errormsg={}",e.getMessage());
        }
        return strs;
    }

    public static byte[] encryptOrDecrypt(int mode,byte[] data,byte[] key){
        try{
            // 强随机数生成器 (RNG)
            SecureRandom random = new SecureRandom();
           // DESKeySpec是一个成加密密钥的密钥内容的（透明）规范的接口。
            DESKeySpec desKey = new DESKeySpec(key);
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 得到密钥对象SecretKey
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(mode, securekey, random);
            // 现在，获取数据并加密，正式执行加密操作
            return cipher.doFinal(data);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        String data = encrypt("18919968585","12345678");
        System.out.println("加密后:"+data);
        System.out.println("解密后："+decrypt(data,"12345678"));
    }

}
