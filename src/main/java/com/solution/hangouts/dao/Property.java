package com.solution.hangouts.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;


@Data
@Entity
@Table(name = "property")
public class Property implements Serializable
{
	@Id
	@SequenceGenerator(
			name = "prop_gen",
			sequenceName = "property_prop_id_seq",
			initialValue = 0
	)
	@GeneratedValue(generator = "property_prop_id_seq", strategy = GenerationType.IDENTITY)
	private long propId;

	@NotBlank
	@Size(max = 10, message = "Property code cannot exceed 10 characters")
	@Column(name = "code")
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
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization organizations;

	//	@JsonManagedReference
	//	@OneToMany(mappedBy = "properties", fetch = FetchType.LAZY)
	//	private Set<PropFacilities> facilities;

}
