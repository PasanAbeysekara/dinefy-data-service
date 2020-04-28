package com.solution.x.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/24/2020 10:36 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StateID implements Serializable
{
	@Column(name = "country_id")
	private Short countryId;

	@Column(name = "state_id")
	private Short stateId;
}
