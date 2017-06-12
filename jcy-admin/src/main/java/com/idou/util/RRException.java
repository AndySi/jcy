package com.idou.util;

/**
 * 自定义异常
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-04-25 下午 2:33
 **/

public class RRException extends RuntimeException {

    private static final long serialVersionUID = 4372306684282244597L;
    private int code = 500;
    private String msg;

    public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
