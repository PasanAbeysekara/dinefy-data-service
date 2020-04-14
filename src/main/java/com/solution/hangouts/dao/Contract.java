package com.solution.hangouts.dao;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.hangouts.ano.GuestFacingName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Entity
@GuestFacingName
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
@ToString
@Table(name = "contracts")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "contractId")
public class Contract implements Serializable
{
	@Id
	@SequenceGenerator(
			name = "contracts_gen",
			sequenceName = "contracts_contract_id_seq",
			initialValue = 0,
			allocationSize = 1
	)
	@GeneratedValue(generator = "contracts_contract_id_seq", strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long contractId;


	@NotBlank
	@Column(name = "version")
	@EqualsAndHashCode.Include
	private Short version;

	@Column(name = "prop_id")
	private Integer propId;

	@Column(name = "timeslot")
	private Short timeSlot;

	@Size(max = 50)
	@Column(name = "version_txt")
	@EqualsAndHashCode.Include
	private String versionTxt;

	//@JsonManagedReference
	@ToString.Exclude
	@OneToOne(mappedBy = "currentContract", fetch = FetchType.LAZY)
	private Property property;

	@ManyToOne
	@JoinColumn(name = "prop_id" , insertable=false, updatable=false )
	private Property contractProp;

}
