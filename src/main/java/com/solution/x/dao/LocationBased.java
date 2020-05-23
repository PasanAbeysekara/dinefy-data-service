package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@Entity
@Table(name = "location_city")
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "locationId")
public class LocationBased
{
	@Id
	@SequenceGenerator(
			name = "location_gen",
			sequenceName = "location_seq",
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "location_gen", strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	@Column(name = "id")
	private Long locationId;

	@Size(max = 50)
	@Column(name = "name")
	private String name;

	//	@Column(name = "country_id")
	//	private Short countryId;
	//
	//	@Column(name = "state_id")
	//	private Short stateId;

	//@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(
			{
					@JoinColumn(name = "country_id", referencedColumnName = "country_id", updatable = false, insertable = false),
					@JoinColumn(name = "state_id", referencedColumnName = "state_id", updatable = false, insertable = false)
			})
	private LocationState state;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "basedLocation", fetch = FetchType.LAZY)
	private Set<Property> properties;

}
