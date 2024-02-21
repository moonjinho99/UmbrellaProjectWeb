package com.example.umbrella.config;

import com.example.umbrella.interceptor.LoggerInterceptor;
import com.example.umbrella.interceptor.LoginCheckInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**","/images/**","/js/**");

        log.debug("LoggerInterceptor is registered.");

//        registry.addInterceptor(new LoginCheckInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/umbrella-login", "/login.do", "/css/**", "/images/**", "/js/**");
    }


}
