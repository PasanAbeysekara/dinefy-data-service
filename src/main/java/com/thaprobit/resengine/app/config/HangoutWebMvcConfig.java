package com.thaprobit.resengine.app.config;

import com.thaprobit.resengine.interceptor.HTTPHeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tharinda Wickramaarachchi
 */
@Configuration
public class HangoutWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HTTPHeaderInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }
}
