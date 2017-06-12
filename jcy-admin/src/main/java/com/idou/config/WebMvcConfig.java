package com.idou.config;

import com.idou.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * MVC配置
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 上午 10:29
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    public AuthorizationInterceptor authorizationInterceptor;

    /**
     * 拦截器
     * 多个拦截器组成一个拦截器链
     * addPathPatterns 用于添加拦截规则,/**对所有请求都拦截
     * excludePathPatterns 用户排除拦截,排除了/toLogin和/login请求的拦截
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册监控拦截器
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/**");
    }
}
