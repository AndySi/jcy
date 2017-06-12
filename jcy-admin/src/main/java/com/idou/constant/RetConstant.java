package com.idou.constant;

/**
 * 返回结果错误码
 *  错误码：1开头
 *  为空码：2开头
 *  异常：3开头
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-04-01 上午 9:29
 **/

public class RetConstant {
    /**用户名错误**/
    public static final int ERROR_NAME = 10001;
    /**密码错误**/
    public static final int ERROR_PWD = 10002;
    /**验证码错误**/
    public static final int ERROR_CAPTCHA = 10003;
    /**用户名为空**/
    public static final int NULL_NAME = 20001;
    /**密码为空**/
    public static final int NULL_PWD = 20002;
    /**帐户锁定**/
    public static final int LOCK_ACCT = 30001;
}
