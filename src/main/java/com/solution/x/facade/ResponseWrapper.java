package com.solution.x.facade;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 3:29 PM
 */

public class ResponseWrapper<T>
{

	private String code;
	private String message;
	private Error error;
	private T data;

	public ResponseWrapper( String code, T data )
	{
		this.code = code;
		this.data = data;
	}

	public ResponseWrapper( String code, String message, Error error )
	{
		this.code = code;
		this.message = message;
		this.error = error;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode( String code )
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public Error getError()
	{
		return error;
	}

	public void setError( Error error )
	{
		this.error = error;
	}

	public T getData()
	{
		return data;
	}

	public void setData( T data )
	{
		this.data = data;
	}
}
