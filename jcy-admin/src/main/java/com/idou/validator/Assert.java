package com.idou.validator;

import com.idou.util.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 *
 * @author ZhangSi
 * @email andy_si@163.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }
}
