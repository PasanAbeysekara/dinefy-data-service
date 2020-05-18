package com.solution.x.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Tharinda Wickramaarachchi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AvailabilityID implements Serializable
{
	@Column(name = "contract_id")
	private Long contractId;

	@Column(name = "avail_unit_id")
	private Integer availUnitId;

	@Column(name = "season_id")
	private Short seasonId;

	@Column(name = "week_def_id")
	private Short weekDefId;


}
