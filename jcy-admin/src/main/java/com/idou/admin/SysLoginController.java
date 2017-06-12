package com.idou.admin;

import com.google.code.kaptcha.Producer;
import com.idou.constant.RetConstant;
import com.idou.util.R;
import com.idou.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 后台用户登录
 *
 * @author zhangSi
 * @email: andy_si@163.com
 * @create 2017-06-05 上午 11:26
 **/
@Controller
public class SysLoginController {
    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    @ResponseBody
    public R login(String username, String password, String captcha) {
        String kaptcha = ShiroUtils.getKaptcha(KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return R.error(RetConstant.ERROR_CAPTCHA, "验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(RetConstant.ERROR_NAME, e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error(RetConstant.ERROR_PWD, e.getMessage());
        } catch (LockedAccountException e) {
            return R.error(RetConstant.LOCK_ACCT, e.getMessage());
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        return R.ok();
    }

    /**
     * 退出
     */
    /*@RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        ShiroUtils.logout();
        return "redirect:login";
    }*/
}
