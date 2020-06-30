package com.solution.x.util;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/28/2020 11:46 PM
 * <p>
 * TODO Code with meaning
 */
public enum SystemMessages
{
	NOT_FOUND( "100", "Requested data not found" ),
	BAD_REQUEST( "101", "Malformed Request" ),
	INVALID_DATA( "102", "Invalid data State" ),
	SUCCESSFULLY_LOADED( "103", "Data successfully loaded" ),

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
	TAG_DELETE_FAILED( "1205", "Tag delete failed" ),

	AVAIL_UNIT_CREATE_SUCCESS( "1300", "Availability Unit successfully saved" ),
	AVAIL_UNIT_CREATE_FAILED( "1301", "Availability Unit saving failed" ),
	AVAIL_UNIT_UPDATE_SUCCESS( "1302", "Availability Unit successfully updated" ),
	AVAIL_UNIT_UPDATE_FAILED( "1303", "Availability Unit update failed" ),
	AVAIL_UNIT_DELETE_SUCCESS( "1304", "Availability Unit successfully deleted" ),
	AVAIL_UNIT_DELETE_FAILED( "1305", "Availability Unit delete failed" ),

	PROPERTY_CREATE_SUCCESS( "1400", "Property successfully saved" ),
	PROPERTY_CREATE_FAILED( "1401", "Property saving failed" ),
	PROPERTY_UPDATE_SUCCESS( "1402", "Property successfully updated" ),
	PROPERTY_UPDATE_FAILED( "1403", "Property update failed" ),
	PROPERTY_DELETE_SUCCESS( "1404", "Property successfully deleted" ),
	PROPERTY_DELETE_FAILED( "1405", "Property delete failed" ),

	PROPERTY_VALIDATION_CONTRACT( "1410", "The contract already associated with a property" ),

	CONTRACT_CREATE_SUCCESS( "1500", "Contract successfully saved" ),
	CONTRACT_CREATE_FAILED( "1501", "Contract saving failed" ),
	CONTRACT_UPDATE_SUCCESS( "1502", "Contract successfully updated" ),
	CONTRACT_UPDATE_FAILED( "1503", "Contract update failed" ),
	CONTRACT_DELETE_SUCCESS( "1504", "Contract successfully deleted" ),
	CONTRACT_DELETE_FAILED( "1505", "Contract delete failed" ),

	PROMOTION_CREATE_SUCCESS( "1600", "Promotion successfully saved" ),
	PROMOTION_CREATE_FAILED( "1601", "Promotion saving failed" ),
	PROMOTION_UPDATE_SUCCESS( "1602", "Promotion successfully updated" ),
	PROMOTION_UPDATE_FAILED( "1603", "Promotion update failed" ),
	PROMOTION_DELETE_SUCCESS( "1604", "Promotion successfully deleted" ),
	PROMOTION_DELETE_FAILED( "1605", "Promotion delete failed" );


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
