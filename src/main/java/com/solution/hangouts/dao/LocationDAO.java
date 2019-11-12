package com.solution.hangouts.dao;

import java.util.Set;

public class LocationDAO
{
	private long locationId;
	private String name; // "Colombo"
	private String geoRegion; //"LK-11"
	private String slug; //"colombo",
	private String link; //"/ads/colombo"

	private Set<SubLocationDAO> subLocationList; //children list

}
