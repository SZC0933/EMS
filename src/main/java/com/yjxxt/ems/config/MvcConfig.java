package com.yjxxt.ems.config;

import com.yjxxt.ems.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }

    /**
     * 添加拦截器
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor())
                // 添加过滤条件
                .addPathPatterns("/**")
                // 设置放行规则
                .excludePathPatterns("/index", "/user/login", "/css/**", "/js/**" ,"/images/**", "/lib/**");
    }
}
