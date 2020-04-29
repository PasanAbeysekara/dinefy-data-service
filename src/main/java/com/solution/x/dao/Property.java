package com.solution.x.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.sys.PaymentOptions;
import com.solution.x.dao.sys.PropertySpeciality;
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
import javax.persistence.JoinTable;
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
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "prop_gen", strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
	@Column(name = "prop_id")
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

	@ToString.Exclude
	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropAvailabilityUnit> availabilityUnits;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "based_location_id")
	private LocationBased basedLocation;

	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "current_cont_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
			@JoinColumn(name = "current_cont_version", referencedColumnName = "version", insertable = false, updatable = false)
	})
	private Contract currentContract;

	@ToString.Exclude
	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropFacilities> facilities;

	@ToString.Exclude
	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropTags> propTags;

	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	private Contacts contacts;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "prop_speciality",
			joinColumns = @JoinColumn(name = "prop_id", referencedColumnName = "prop_id", insertable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
	)
	private Set<PropertySpeciality> propertySpecialities;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "prop_payment_options",
			joinColumns = @JoinColumn(name = "prop_id", referencedColumnName = "prop_id", insertable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "option_id")
	)
	private Set<PaymentOptions> paymentOptions;

	//@OneToMany(mappedBy = "contractProp", fetch = FetchType.LAZY)
	//private Set<Contract> allContracts;

}
