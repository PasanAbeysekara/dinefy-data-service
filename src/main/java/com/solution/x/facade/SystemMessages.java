package com.solution.x.facade;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 11:46 PM
 * <p>
 * TODO Code with meaning
 */
public enum SystemMessages
{
	NOT_FOUND( "100", "Requested data not found" ),

	FACILITY_CREATE_SUCCESS( "1100", "Facility successfully saved" ),
	FACILITY_CREATE_FAILED( "1101", "Facility saving failed" ),
	FACILITY_UPDATE_SUCCESS( "1102", "Facility successfully updated" ),
	FACILITY_UPDATE_FAILED( "1103", "Facility update failed" ),
	FACILITY_DELETE_SUCCESS( "1104", "Facility successfully deleted" ),
	FACILITY_DELETE_FAILED( "1105", "Facility delete failed" ),

	TAG_CREATE_SUCCESS( "1200", "Tag successfully saved" ),
	TAG_CREATE_FAILED( "1201", "Tag saving failed" ),
	TAG_UPDATE_SUCCESS( "1202", "Tag successfully updated" ),
	TAG_UPDATE_FAILED( "1203", "Tag update failed" ),
	TAG_DELETE_SUCCESS( "1204", "Tag successfully deleted" ),
	TAG_DELETE_FAILED( "1205", "Tag delete failed" );

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
