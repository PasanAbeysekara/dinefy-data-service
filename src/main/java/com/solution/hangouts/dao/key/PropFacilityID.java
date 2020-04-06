package com.solution.hangouts.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PropFacilityID implements Serializable
{
	@Column(name = "prop_id")
	private int propId;

	@Column(name = "facility_id")
	private int facility_id;
}
