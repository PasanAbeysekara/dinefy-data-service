package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/24/2020 10:21 PM
 */

@Data
@Entity
@ToString
@Table(name = "location_country")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "countryId")
public class LocationCountry
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id", updatable = false, nullable = false)
	private Short countryId;

	@Size(max = 50)
	@Column(name = "name")
	private String name;

	@ToString.Exclude
	@JsonBackReference
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private Set<LocationState> states;
}
