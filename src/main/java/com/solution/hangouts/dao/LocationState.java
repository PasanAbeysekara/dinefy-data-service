package com.solution.hangouts.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.solution.hangouts.dao.key.StateID;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/24/2020 10:20 PM
 */
@Data
@Entity
@Table(name = "location_state")
public class LocationState
{
	@EmbeddedId
	private StateID stateId;

	@Size(max = 50)
	@Column(name = "name")
	private String name;

	@JsonBackReference
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private Set<LocationBased> basedLocation;

	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("countryId")
	@JoinColumn(name = "country_id")
	private LocationCountry country;

}
