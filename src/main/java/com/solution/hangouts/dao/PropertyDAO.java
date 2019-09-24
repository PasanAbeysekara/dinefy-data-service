package com.solution.hangouts.dao;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "property")
public class PropertyDAO
{
	@Id
	@SequenceGenerator(
			name = "prop_gen",
			sequenceName = "property_prop_id_seq",
			initialValue = 100
	)
	private long propId;

	@NotBlank
	@Size(max = 10)
	private String code;

	@Size(max = 100)
	private String name;

	@ManyToMany( mappedBy = "ownProperties", fetch = FetchType.EAGER )
	Set<OrganizationDAO> organization;


}
