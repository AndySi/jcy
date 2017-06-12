package com.idou.api;

import com.idou.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信接入
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-12 上午 10:25
 **/
@RestController
@RequestMapping("/wechat")
public class WechatController {
    private static Logger logger = LoggerFactory.getLogger(WechatController.class);

    /**
     * 验证是否来自微信服务器的消息
     *
     * @param signature
     * @param nonce
     * @param timestamp
     * @param echostr
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(@RequestParam(name = "signature", required = false) String signature,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "echostr", required = false) String echostr) {
        if (SignUtil.validateSignature(signature, timestamp, nonce)) {
            logger.info("接入成功");
            return echostr;
        }
        logger.info("接入失败");
        return "";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
