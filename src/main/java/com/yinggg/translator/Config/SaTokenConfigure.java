package com.yinggg.translator.Config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
        // 注册拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 注册 Sa-Token 的路由拦截器
            registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                    .addPathPatterns("/**")
                    .excludePathPatterns("/login","/register","/api","/generateCaptcha","/smsCode");
        }

    @Bean
    public StpLogic getStpLogicJwt() {
        // 混合模式
        // return new StpLogicJwtForMixin();
        // 无状态模式
        // return new StpLogicJwtForStateless();
        // (Simple 简单模式)
        return new StpLogicJwtForSimple();
    }

}


