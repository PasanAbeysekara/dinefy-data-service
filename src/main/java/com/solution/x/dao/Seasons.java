package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.solution.x.ano.GuestFacingName;
import com.solution.x.dao.key.SeasonID;
import com.solution.x.dao.sys.WeekDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
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
//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator.class,
//		property = "seasonId")
public class Seasons
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private SeasonID seasonId;

	@NotBlank
	@Size(max = 10, message = "Season code cannot exceed 10 characters")
	@Column(name = "code")
	@EqualsAndHashCode.Include //Lombok HashCode issues , Need to explicitly add include with onlyExplicitlyIncluded = true
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "from")
	private Date from;

	@Column(name = "to")
	private Date to;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "contract_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
			@JoinColumn(name = "contract_version", referencedColumnName = "version", insertable = false, updatable = false)
	})
	private Contract contract;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "contract_availability",
			joinColumns = {
					@JoinColumn(name = "contract_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
					@JoinColumn(name = "contract_version", referencedColumnName = "contract_version", insertable = false, updatable = false),
					@JoinColumn(name = "season_id", referencedColumnName = "season_id", insertable = false, updatable = false)},
			inverseJoinColumns = @JoinColumn(name = "week_def_id")
	)
	private Set<WeekDefinition> weekDefinitions;

	@JsonManagedReference
	@OneToMany(mappedBy = "seasons", fetch = FetchType.LAZY)
	private Set<ContractAvailability> availabilities;
}

