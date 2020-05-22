package com.solution.x.facade;

import com.solution.x.global.SystemOperation;
import lombok.Data;

/**
 * API Response wrapper
 *
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

	/**
	 * Constructor with Data for success response
	 *
	 * @param operation      SystemOperation
	 * @param systemMessages SystemMessages
	 * @param data           Data
	 */
	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, T data )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase();
		this.data = data;
	}

	/**
	 * Constructor with message appender for error response : Suitable for validations
	 *
	 * @param operation       SystemOperation
	 * @param systemMessages  SystemMessages
	 * @param messageAppender extra message to append
	 */
	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, String messageAppender )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase() + " : " + messageAppender;
	}

	/**
	 * Constructor with Error object for error response : Suitable for exceptions
	 *
	 * @param operation      SystemOperation
	 * @param systemMessages SystemMessages
	 * @param error          Error object
	 */
	public ResponseWrapper( SystemOperation operation, SystemMessages systemMessages, Error error )
	{
		this.operation = operation.toString();
		this.status = operation.status() ? SUCCESS : FAIL;
		this.code = systemMessages.code();
		this.prettyMessage = systemMessages.getReasonPhrase();
		this.error = error;
	}

}
