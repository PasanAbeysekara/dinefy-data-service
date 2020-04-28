package com.solution.x.facade;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 11:46 PM
 * <p>
 * TODO Code with meaning
 */
public enum SystemMessages
{

	FACILITY_CREATE_SUCCESS( "100", "Facility successfully saved" ),
	FACILITY_CREATE_FAILED( "101", "Facility saving failed" ),
	FACILITY_UPDATE_FAILED( "102", "Facility update failed" ),
	FACILITY_DELETE_FAILED( "103", "Facility delete failed" );

	private final String code;
	private final String reasonPhrase;


	SystemMessages( String code, String reasonPhrase )
	{
		this.code = code;
		this.reasonPhrase = reasonPhrase;
	}

	public String code()
	{
		return this.code;
	}

	public String getReasonPhrase()
	{
		return this.reasonPhrase;
	}
}
