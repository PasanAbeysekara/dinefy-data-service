package com.solution.hangouts.dao.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sys_facilities")
public class Facilities
{
	@Id
	@SequenceGenerator(
			name = "facilities_gen",
			sequenceName = "facilities_facility_id_seq",
			initialValue = 100
	)
	private int facility_id;

	@NotBlank
	@Size(max = 10)
	@Column(name = "code")
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;
}
