package com.solution.hangouts.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.hangouts.ano.GuestFacingName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@GuestFacingName
@Table(name = "organization")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "orgId")
public class Organization implements Serializable
{
	@Id
	@SequenceGenerator(
			name = "organization_gen",
			sequenceName = "organization_org_id_seq",
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "organization_org_id_seq", strategy = GenerationType.IDENTITY)
	private long orgId;

	@NotBlank
	@Size(max = 10)
	private String code;

	@Size(max = 100)
	private String name;

	//@JsonManagedReference
	@OneToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
	private Set<Property> properties;

//	public long getOrgId()
//	{
//		return orgId;
//	}
//
//	public void setOrgId( long orgId )
//	{
//		this.orgId = orgId;
//	}
//
//	public String getCode()
//	{
//		return code;
//	}
//
//	public void setCode( String code )
//	{
//		this.code = code;
//	}
//
//	public String getName()
//	{
//		return name;
//	}
//
//	public void setName( String name )
//	{
//		this.name = name;
//	}
//
//	public Set<Property> getProperties()
//	{
//		return properties;
//	}
//
//	public void setProperties( Set<Property> properties )
//	{
//		this.properties = properties;
//	}
}
