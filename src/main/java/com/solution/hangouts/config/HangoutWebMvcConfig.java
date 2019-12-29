package com.solution.hangouts.config;

import com.solution.hangouts.interceptor.HTTPHeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HangoutWebMvcConfig implements WebMvcConfigurer
{
	@Override
	public void addInterceptors( InterceptorRegistry registry )
	{
		registry.addInterceptor( new HTTPHeaderInterceptor() );
	}

}
