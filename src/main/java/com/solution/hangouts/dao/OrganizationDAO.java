package com.solution.hangouts.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "organization")
public class OrganizationDAO
{
	@Id
	@SequenceGenerator(
			name = "organization_gen",
			sequenceName = "organization_org_id_seq",
			initialValue = 100
	)
	private long orgId;

	@NotBlank
	@Size(max = 10)
	private String code;

	@Size(max = 100)
	private String name;

	@OneToMany(mappedBy = "organizations", fetch = FetchType.EAGER )
	@JsonManagedReference
	private Set<PropertyDAO> ownProperties;


	public long getOrgId()
	{
		return orgId;
	}

	public void setOrgId( long orgId )
	{
		this.orgId = orgId;
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

	public Set<PropertyDAO> getOwnProperties()
	{
		return ownProperties;
	}

	public void setOwnProperties( Set<PropertyDAO> ownProperties )
	{
		this.ownProperties = ownProperties;
	}
}
