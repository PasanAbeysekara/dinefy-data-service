package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.key.PropAvailabilityUnitKey;
import com.solution.x.dao.sys.AvailabilityUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@Entity
@Table(name = "prop_availability_unit")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "propAvailabilityUnitId")
@ToString
public class PropAvailabilityUnit extends RepresentationModel<PropAvailabilityUnit>
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private PropAvailabilityUnitKey propAvailabilityUnitId;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "capacity")
	private Short capacity;

	//@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("unit_id")
	@JoinColumn(name = "unit_id")
	@ToString.Exclude
	private AvailabilityUnit sysAvailabilityUnit;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("propId")
	@JoinColumn(name = "prop_id")
	@ToString.Exclude
	private Property properties;
}
