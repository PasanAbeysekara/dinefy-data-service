package com.solution.hangouts.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.hangouts.ano.GuestFacingName;
import lombok.Data;

import javax.persistence.Column;
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
@Table(name = "contracts")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "contractId")
public class Contracts implements Serializable
{
	@Id
	@SequenceGenerator(
			name = "contracts_gen",
			sequenceName = "contracts_contract_id_seq",
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "contracts_contract_id_seq", strategy = GenerationType.IDENTITY)
	private long contractId;


	@NotBlank
	@Column(name = "version")
	private Short version;

	@Column(name = "timeslot")
	private Short timeSlot;

	@Size(max = 50)
	@Column(name = "version_txt")
	private String versionTxt;

	//@JsonManagedReference
	@OneToMany(mappedBy = "organizations", fetch = FetchType.LAZY)
	private Set<Property> properties;
}
