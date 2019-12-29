package com.solution.hangouts.dao;


import com.fasterxml.jackson.annotation.JsonBackReference;

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

@Entity
@Table(name = "property")
public class PropertyDAO
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


	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private OrganizationDAO organizations;

	public long getPropId()
	{
		return propId;
	}

	public void setPropId( long propId )
	{
		this.propId = propId;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode( String code )
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public OrganizationDAO getOrganizations()
	{
		return organizations;
	}

	public void setOrganizations( OrganizationDAO organizations )
	{
		this.organizations = organizations;
	}
}
