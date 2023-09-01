package com.thaprobit.resengine.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Tharinda Wickramaarachchi
 */
public class HTTPHeaderInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception ) throws Exception
	{
		System.out.println( "[HTTPHeaderInterceptor] After Completion" );
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception
	{
		response.setHeader( "Access-Control-Allow-Origin2aa", "http://localhost:4200" );
		response.setHeader( "xxx-zzz", "sasas" );
		System.out.println( "[HTTPHeaderInterceptor] Post Handle" );
	}

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{

		System.out.println( "[HTTPHeaderInterceptor] Pre Handle" );

		return true;
	}


}
