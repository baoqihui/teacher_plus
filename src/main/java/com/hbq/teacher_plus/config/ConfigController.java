package com.hbq.teacher_plus.config;

import com.hbq.teacher_plus.util.BufferImage;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
public class ConfigController {
    /* **********************************直接访问首页************************************* */
    @Configuration
    public class WebConfigurer implements WebMvcConfigurer {
        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("forward:/login.html");
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        }
    }
    /* **********************************图片验证码************************************* */
    @RequestMapping(value = "/BufferImage")
    protected void createImg(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1.生成随机的验证码及图片
        Object[] objs = BufferImage.createImage();
        //2.将验证码存入session
        String imgcode = (String) objs[0];
        HttpSession session = req.getSession();
        session.setAttribute("checkcode", imgcode);
        //3.将图片输出给浏览器
        BufferedImage img = (BufferedImage) objs[1];
        res.setContentType("image/png");
        //服务器自动创建输出流，目标指向浏览器
        OutputStream os = res.getOutputStream();
        ImageIO.write(img, "png", os);
        os.close();
    }
}
