package com.nefu.workmanage;


import com.nefu.workmanage.interceptor.APIInterceptor;
import com.nefu.workmanage.interceptor.LoginInterceptor;
//import com.nefu.workmanage.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
   @Autowired
    private APIInterceptor apiInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        /*registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");*/
    }
}
