package com.hbq.teacher_plus.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    private UserInterceptor userInterceptor;
    @Autowired
    private UserFilter userFilter;

    /**
     * 拦截器
     * */
    /*@Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
            public void addInterceptors(InterceptorRegistry registry) {
                //排除无需拦截路径
                List<String> l=new ArrayList<>();
                l.add("/userLogin.do");
                l.add("/sendMessage.do");
                l.add("/users");
                //配置需要拦截路径/*.do
                //registry.addInterceptor(userInterceptor).addPathPatterns("/controller/**").excludePathPatterns(l);
            }
        };
    }*/


    /**
     * 过滤器
     * */

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(userFilter);
        //配置需要过滤路径
        registration.addUrlPatterns("/index/*");
        registration.addUrlPatterns("/manage/*");
        registration.addUrlPatterns("/teacher/*");

        registration.setName("userFilter");
        return registration;
    }
}
