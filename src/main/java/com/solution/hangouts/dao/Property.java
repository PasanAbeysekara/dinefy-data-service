package com.solution.hangouts.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
@ToString
@Entity
@Table(name = "property")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "propId")
public class Property extends RepresentationModel<Property>
{
	@Id
	@SequenceGenerator(
			name = "prop_gen",
			sequenceName = "property_prop_id_seq",
			initialValue = 0
	)
	@GeneratedValue(generator = "property_prop_id_seq", strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
	private long propId;

	@NotBlank
	@Size(max = 10, message = "Property code cannot exceed 10 characters")
	@Column(name = "code")
	@EqualsAndHashCode.Include //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Size(min = 10)
	@Column(name = "description")
	private String description;

	@Size(max = 100)
	@Column(name = "location_name")
	private String location_name;

	//	@Embedded
	//	@AttributeOverrides({
	//			@AttributeOverride(name = "geo_location", column = @Column(name = "x")),
	//			@AttributeOverride(name = "geo_location", column = @Column(name = "y"))
	//	})
	//	private Point point;


	//	@Column(name = "geo_location" )
	//	private Point geo_location;

	@Column(name = "current_cont_id")
	private Integer current_cont_id;

	@Column(name = "current_cont_version")
	private Short current_cont_version;

	@Column(name = "start_time")
	private LocalTime start_time;

	//@JsonIgnore
	@Column(name = "end_time")
	private LocalTime end_time;

	@JsonBackReference
	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization organizations;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropAvailabilityUnit> availabilityUnits;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropFacilities> facilities;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropTags> propTags;


	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "current_cont_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
			@JoinColumn(name = "current_cont_version", referencedColumnName = "version", insertable = false, updatable = false)
	})
	private Contract currentContract;

	//@OneToMany(mappedBy = "contractProp", fetch = FetchType.LAZY)
	//private Set<Contract> allContracts;

}
