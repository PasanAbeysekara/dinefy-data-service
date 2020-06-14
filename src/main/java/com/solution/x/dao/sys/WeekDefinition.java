package com.solution.x.dao.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.DayOfWeek;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "week_definition")
public class WeekDefinition
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "week_def_id", updatable = false, nullable = false)
	private Short weekDefId;

	@NotBlank
	@Size(max = 10)
	@Column(name = "code")
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "week")
	@Size(max = 7)
	private String week;

	@Transient
	//TODO Cache this value using ehcache
	private Boolean[] weekDays;

	public Boolean[] getWeekDays()
	{
		return new Boolean[]{week.charAt( 0 ) == '1', week.charAt( 1 ) == '1', week.charAt( 2 ) == '1', week.charAt( 3 ) == '1', week.charAt( 4 ) == '1', week.charAt( 5 ) == '1', week.charAt( 6 ) == '1'};
	}

	public boolean isValidDay( DayOfWeek dayOfWeek )
	{
		return week.charAt( dayOfWeek.getValue() - 1 ) == '1'; // day-of-week, from 1 (Monday) to 7 (Sunday)
	}

	//	@JsonBackReference // Could not write JSON: Infinite recursion (StackOverflowError)
	//	@OneToMany(mappedBy = "weekDefinition", fetch = FetchType.LAZY)
	//	@PrimaryKeyJoinColumn
	//	private Set<ContractAvailability> contractAvailabilities;

	//	@JsonBackReference
	//	@OneToMany(fetch = FetchType.LAZY)
	//	@JoinTable(
	//			name = "contract_availability",
	//			inverseJoinColumns = {
	//					@JoinColumn(name = "contract_id", referencedColumnName = "contract_id", insertable = false, updatable = false),
	//					@JoinColumn(name = "season_id", referencedColumnName = "season_id", insertable = false, updatable = false)},
	//			joinColumns = @JoinColumn(name = "week_def_id", insertable = false, updatable = false)
	//	)
	//	private Set<Seasons> seasons;

}
