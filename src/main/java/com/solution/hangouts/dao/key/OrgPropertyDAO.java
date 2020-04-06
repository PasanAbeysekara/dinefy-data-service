package com.solution.hangouts.dao.key;

import com.solution.hangouts.dao.Property;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "organization")
public class OrgPropertyDAO
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


	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "org_properties",
			joinColumns = @JoinColumn(name = "org_id"),
			inverseJoinColumns = @JoinColumn(name = "prop_id"))
	Set<Property> ownProperties;


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
}
