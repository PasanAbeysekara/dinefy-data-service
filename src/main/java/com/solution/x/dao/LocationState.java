package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.key.StateID;
import lombok.Data;
import lombok.ToString;

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
@ToString
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "stateId")
public class LocationState
{
	@EmbeddedId
	private StateID stateId;

	@Size(max = 50)
	@Column(name = "name")
	private String name;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	private Set<LocationBased> basedLocation;

	//@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("countryId")
	@JoinColumn(name = "country_id")
	private LocationCountry country;

}
