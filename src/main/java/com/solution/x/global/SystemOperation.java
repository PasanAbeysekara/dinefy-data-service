package com.solution.x.global;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/9/2020 12:21 AM
 */
public enum SystemOperation
{
	READ,
	CREATE,
	MODIFY,
	DELETE,
	VALIDATE,
	Deserialize;

	private boolean status = false;

	public SystemOperation withError()
	{
		this.status = false;
		return this;
	}

	public SystemOperation withSuccess()
	{
		this.status = true;
		return this;
	}

	public boolean status()
	{
		return this.status;
	}
}
