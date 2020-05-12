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
	private long contractId;

	@Column(name = "contract_version")
	private Short version;

	//	@Column(name = "prop_id")
	//	private int propId;

	@Column(name = "avail_unit_id")
	private int availUnitId;

	@Column(name = "season_id")
	private Short seasonId;

	@Column(name = "week_def_id")
	private Short weekDefId;


}
