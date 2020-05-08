package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.ano.GuestFacingName;
import com.solution.x.dao.key.ContractID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@Entity
@GuestFacingName
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
@ToString
@Table(name = "contracts")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "contractId")
public class Contract extends RepresentationModel<Contract>
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private ContractID contractId;

	@Column(name = "prop_id")
	private Integer propId;

	@Column(name = "timeslot")
	private Short timeSlot;

	@Size(max = 50)
	@Column(name = "version_txt")
	@EqualsAndHashCode.Include
	private String versionTxt;

	@JsonBackReference
	@ToString.Exclude
	@OneToOne(mappedBy = "currentContract", fetch = FetchType.LAZY)
	@JoinColumn(name = "prop_id")
	private Property property;

	//	@ManyToOne(fetch = FetchType.LAZY)
	//	@JoinColumn(name = "prop_id", insertable = false, updatable = false)
	//	private Property contractProp;

	//@JsonManagedReference
	@OneToMany(mappedBy = "contract", fetch = FetchType.LAZY)
	private Set<Seasons> seasons;
}
