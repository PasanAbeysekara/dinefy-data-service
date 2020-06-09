package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.ano.GuestFacingName;
import com.solution.x.dao.key.SeasonID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/18/2020 10:56 AM
 */
@Data
@Entity
@GuestFacingName
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
@ToString
@Table(name = "contract_season")
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "seasonId")
public class Seasons
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private SeasonID seasonId;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "from_date")
	private LocalDate from;

	@Column(name = "to_date")
	private LocalDate to;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contract_id", referencedColumnName = "contract_id", insertable = false, updatable = false)
	//@MapsId("contractId")
	private Contract contract;


	//	@OneToMany(fetch = FetchType.LAZY)
	//	@JoinTable(
	//			name = "contract_availability",
	//			joinColumns = {
	//					@JoinColumn(name = "contract_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
	//					@JoinColumn(name = "season_id", referencedColumnName = "season_id", insertable = false, updatable = false)},
	//			inverseJoinColumns = @JoinColumn(name = "week_def_id", insertable = false, updatable = false)
	//	)
	//	private Set<WeekDefinition> weekDefinitions;

	//@JsonManagedReference
	@OneToMany(mappedBy = "seasons", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ContractAvailability> availabilities;
}

