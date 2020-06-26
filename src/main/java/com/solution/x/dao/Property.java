package com.solution.x.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.sys.PaymentOptions;
import com.solution.x.dao.sys.PropertySpeciality;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	//	@SequenceGenerator(
	//			name = "prop_gen",
	//			sequenceName = "property_prop_id_seq",
	//			initialValue = 0,
	//			allocationSize = 1
	//	)
	//	@GeneratedValue(generator = "prop_gen", strategy = GenerationType.SEQUENCE)
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
	private Integer currentContId;

	@Column(name = "start_time")
	private LocalTime startTime;

	//@JsonIgnore
	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name = "timeslot")
	private Short timeSlotMinutes; // In minutes , 30 (1/2 hr), 60 (1hr) , 90 (1 1/2 hr) , 120 (2hr)

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("time_start ASC")
	private Set<OperationHours> operationHours;

	@ToString.Exclude
	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	private Set<PropAvailabilityUnit> availabilityUnits;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "based_location_id")
	@ToString.Exclude
	private LocationBased basedLocation;

	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_cont_id", referencedColumnName = "contract_id", insertable = false, updatable = false)
	private Contract currentContract;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PropFacilities> facilities;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<PropTags> tags;

	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id")
	private ContactDetails contactDetails;

	//@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "prop_speciality",
			joinColumns = @JoinColumn(name = "prop_id", referencedColumnName = "prop_id", insertable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "speciality_id")
	)
	private Set<PropertySpeciality> propertySpecialities;

	//@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "prop_payment_options",
			joinColumns = @JoinColumn(name = "prop_id", referencedColumnName = "prop_id", insertable = false, updatable = false),
			inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "option_id")
	)
	private Set<PaymentOptions> paymentOptions;

	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	@Where(clause = "live = true")
	private Set<Promotion> livePromotions;

	@JsonBackReference
	@ToString.Exclude
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization organizations;

	@Transient
	//TODO Cache this value using ehcache
	private List<LocalTime> timeSlots;

	public List<LocalTime> getTimeSlots()
	{

		List<OperationHours> sortedOperationHours = operationHours.stream().sorted( Comparator.comparing( OperationHours::getTimeStart ) ).collect( Collectors.toList() );

		List<LocalTime> timeSlots = new ArrayList<>();

		for( OperationHours operationHour : sortedOperationHours )
		{
			LocalTime timeStart = operationHour.getTimeStart();
			timeSlots.add( timeStart );

			LocalTime currentTime = timeStart;
			while( currentTime.isBefore( operationHour.getTimeEnd().minusMinutes( timeSlotMinutes ) ) )
			{
				currentTime = currentTime.plusMinutes( timeSlotMinutes );
				timeSlots.add( currentTime );
			}
		}

		return timeSlots;

	}

}
