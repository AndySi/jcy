package com.idou.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-12 上午 10:31
 **/

public class SignUtil {
    // 与接口配置信息中的Token要一致
    private static String token = "JavaApe";

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean validateSignature(String signature, String timestamp, String nonce) {
        String content = sort(token, timestamp, nonce);

        String mySignature = encrypt(content);

        return compareSignature(mySignature, signature);
    }

    /**
     * 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param mySignature
     * @param paramSignature
     * @return
     */
    private static boolean compareSignature(String mySignature, String paramSignature) {
        if ((mySignature == null) || ("".equals(mySignature))) {
            return false;
        }
        if ((paramSignature == null) || ("".equals(paramSignature))) {
            return false;
        }
        return mySignature.equals(paramSignature.toUpperCase());
    }

    /**
     * 字典序排序
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String sort(String token, String timestamp, String nonce) {
        String[] arr = {token, timestamp, nonce};

        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for (String param : arr) {
            content.append(param);
        }

        return content.toString();
    }

    /**
     * sha1加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        MessageDigest md = null;
        String retVal = null;
        try {
            md = MessageDigest.getInstance("SHA-1");

            byte[] digest = md.digest(content.toString().getBytes());
            retVal = byteToStr(digest);
            return retVal;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; ++i) {
            strDigest = strDigest + byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4 & 0xF)];
        tempArr[1] = Digit[(mByte & 0xF)];

        String s = new String(tempArr);
        return s;
    }
}
