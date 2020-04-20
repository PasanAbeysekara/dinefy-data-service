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
public class PropAvailabilityUnitKey implements Serializable
{
	@Column(name = "prop_id")
	private int propId;

	@Column(name = "unit_id")
	private int unit_id;
}
