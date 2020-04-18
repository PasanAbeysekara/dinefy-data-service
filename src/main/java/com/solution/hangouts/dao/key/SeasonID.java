package com.solution.hangouts.dao.key;

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
public class SeasonID implements Serializable
{
	@Column(name = "contract_id")
	private long contractId;

	@Column(name = "contract_version")
	private Short version;

	@Column(name = "season_id")
	private Short seasonId;
}
