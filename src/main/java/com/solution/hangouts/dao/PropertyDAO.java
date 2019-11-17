package com.solution.hangouts.dao;


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
	@GeneratedValue( generator = "property_prop_id_seq" , strategy= GenerationType.IDENTITY )
	private long propId;

	@NotBlank
	@Size(max = 10 , message = "Property code cannot exceed 10 characters")
	@Column(name = "code")
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "org_id"	)
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

	public OrganizationDAO getOrganizations()
	{
		return organizations;
	}

	public void setOrganizations( OrganizationDAO organizations )
	{
		this.organizations = organizations;
	}
}
