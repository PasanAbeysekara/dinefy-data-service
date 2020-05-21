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
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "FAIL";

	private String code;
	private String status;
	private String operation;
	private String prettyMessage;
	private Error error;
	private T data;

	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, T data )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase();
		this.data = data;
	}

	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, String messageAppender )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase() + " : " + messageAppender;
	}

	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, Error error )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase();
		this.error = error;
	}

}
