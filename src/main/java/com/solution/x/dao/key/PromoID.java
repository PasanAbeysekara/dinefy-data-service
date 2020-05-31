package com.solution.x.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/31/2020 6:30 PM , Watching SpaceX - Crew Demo-2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PromoID implements Serializable
{
	@Column(name = "prop_id")
	private Long propId;

	@Column(name = "promo_id")
	private Short promoId;
}
