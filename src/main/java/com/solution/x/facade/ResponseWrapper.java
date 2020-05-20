package com.solution.x.facade;

import com.solution.x.global.SystemOperation;
import lombok.Data;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 3:29 PM
 */

@Data
public class ResponseWrapper<T>
{

	private String code;
	private String status;
	private String operation;
	private String prettyMessage;
	private Error error;
	private T data;

	public ResponseWrapper( String code, T data )
	{
		this.code = code;
		this.data = data;
	}

	@Deprecated
	public ResponseWrapper( String code, String prettyMessage, T data )
	{
		this.code = code;
		this.prettyMessage = prettyMessage;
		this.data = data;
	}

	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, T data )
	{
		this.operation = operation.toString();
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase();
		this.data = data;
	}

	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, String messageAppender )
	{
		this.operation = operation.toString();
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase() + " : " + messageAppender;
	}

	@Deprecated
	public ResponseWrapper( String code, String prettyMessage, Error error )
	{
		this.code = code;
		this.prettyMessage = prettyMessage;
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

	public String getOperation()
	{
		return operation;
	}

	public void setOperation( String operation )
	{
		this.operation = operation;
	}

}
