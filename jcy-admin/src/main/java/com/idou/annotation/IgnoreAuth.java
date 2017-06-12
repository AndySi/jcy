package com.idou.annotation;

import java.lang.annotation.*;

/**
 * 忽略验证
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 上午 10:40
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {
}
