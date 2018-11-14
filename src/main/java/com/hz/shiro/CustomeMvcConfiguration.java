package com.hz.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author lyp
 * @date 2018/11/12
 */
public class CustomeMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public AjaxAuthorizationFilter ajaxAuthorizationFilter(){
        return new AjaxAuthorizationFilter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry interceptor){
        interceptor.addInterceptor((HandlerInterceptor) ajaxAuthorizationFilter());
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer){
        asyncSupportConfigurer.registerCallableInterceptors((CallableProcessingInterceptor) ajaxAuthorizationFilter());

    }

}
