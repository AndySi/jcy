package com.idou.config;

import com.alibaba.fastjson.JSON;
import com.idou.util.R;
import com.idou.util.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通用Exception处理
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 上午 10:24
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception ex, HttpServletResponse response) {
        R r = new R();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            if (ex instanceof RRException) {
                r.put("code", ((RRException) ex).getCode());
                r.put("msg", ex.getMessage());
            } else {
                r = R.error();
            }
            logger.error("记录异常", ex);
            String json = JSON.toJSONString(r);
            response.getWriter().print(json);
        } catch (IOException e) {
            logger.error("RRExceptionHandler 异常处理失败", e);
        }
        return new ModelAndView();
    }
}
