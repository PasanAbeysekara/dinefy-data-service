package com.solution.x.facade;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 3:30 PM
 */
public class Error
{
	private String code;
	private String message;
	private List<String> errorList;

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

	public void addMessage( String message )
	{
		if( this.errorList == null )
		{
			this.errorList = new ArrayList<>();
		}

		this.errorList.add( message );
	}

	public List<String> getErrorList()
	{
		return errorList;
	}

	public void setErrorList( List<String> errorList )
	{
		this.errorList = errorList;
	}
}
