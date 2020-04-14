package com.solution.hangouts.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tharinda Wickramaarachchi
 */
public class HTTPHeaderInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception ) throws Exception
	{
		System.out.println( "afterCompletion" );
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception
	{
		response.setHeader( "Access-Control-Allow-Origin2aa", "http://localhost:4200" );
		response.setHeader( "xxx-zzz", "sasas" );
		System.out.println( "postHandle" );
	}

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{

		System.out.println( "preHandle" );

		return true;
	}


}
