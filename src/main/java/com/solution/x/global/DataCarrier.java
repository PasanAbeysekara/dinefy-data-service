package com.solution.x.global;

import lombok.Data;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/8/2020 11:49 PM
 */
@Data
public class DataCarrier<T>
{
	private CarrierStatus status = CarrierStatus.NONE;
	private String message;
	private T data;
	private Exception exception;

	public static <DC> DataCarrier<DC> init()
	{
		return new DataCarrier<>();
	}

	public boolean isSuccess()
	{
		return CarrierStatus.SUCCESS.equals( status );
	}

	public boolean isError()
	{
		return CarrierStatus.ERROR.equals( status );
	}

	public DataCarrier<T> withError()
	{
		this.status = CarrierStatus.ERROR;
		return this;
	}

	public DataCarrier<T> withSuccess()
	{
		this.status = CarrierStatus.SUCCESS;
		return this;
	}

	public DataCarrier<T> setMessage( String message )
	{
		this.message = message;
		return this;
	}

	public DataCarrier<T> setException( Exception exception )
	{
		this.exception = exception;
		return this;
	}

	public enum CarrierStatus
	{
		NONE, SUCCESS, ERROR;
	}
}
