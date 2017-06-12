package com.idou.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注释
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 下午 3:22
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
