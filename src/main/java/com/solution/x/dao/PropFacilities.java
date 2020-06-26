package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.key.PropFacilityID;
import com.solution.x.dao.sys.Facilities;
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
@Table(name = "prop_facilities")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "propFacilityId")
@ToString
public class PropFacilities extends RepresentationModel<PropFacilities>
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private PropFacilityID propFacilityId;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	//@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("facility_id")
	@JoinColumn(name = "facility_id")
	@ToString.Exclude
	private Facilities sysFacility;

	@ManyToOne(fetch = FetchType.LAZY)
	//@MapsId("propId")
	@JoinColumn(name = "prop_id", insertable = false, updatable = false) // attempted to assign id from null one-to-one property , remove @MapsId("propId")
	@ToString.Exclude
	private Property properties;
}
