package com.solution.x.data.app.config;

import com.solution.x.data.interceptor.HTTPHeaderInterceptor;
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
