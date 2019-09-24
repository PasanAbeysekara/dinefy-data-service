package com.solution.hangouts.dao.key;

import com.solution.hangouts.dao.OrganizationDAO;
import com.solution.hangouts.dao.PropertyDAO;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

public class Owns
{
	OrgProp id;

//	@ManyToOne
//	@MapsId("org_id")
//	@JoinColumn(name = "org_id")
//	OrganizationDAO organization;
//
//	@ManyToOne
//	@MapsId("prop_id")
//	@JoinColumn(name = "prop_id")
//	PropertyDAO property;

	int owns;

}
