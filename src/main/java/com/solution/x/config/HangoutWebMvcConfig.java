package com.solution.x.config;

import com.solution.x.interceptor.HTTPHeaderInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Tharinda Wickramaarachchi
 */
@Configuration
public class HangoutWebMvcConfig implements WebMvcConfigurer
{
	@Override
	public void addInterceptors( InterceptorRegistry registry )
	{
		registry.addInterceptor( new HTTPHeaderInterceptor() );
	}

}
