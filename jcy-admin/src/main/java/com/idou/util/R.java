package com.idou.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-03-31 下午 2:55
 **/

public class R extends HashMap<String, Object> {
    public R() {
        put("code", 0);
    }

    /**
     * 返回错误
     *
     * @return
     */
    public static R error() {
        return error(500, "未知异常，请联系管理员");
    }

    /**
     * 返回错误
     *
     * @param msg
     * @return
     */
    public static R error(String msg) {
        return error(500, msg);
    }

    /**
     * 返回错误
     *
     * @param code
     * @param msg
     * @return
     */
    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * 返回成功
     *
     * @param msg
     * @return
     */
    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /**
     * 返回成功
     *
     * @param map
     * @return
     */
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
