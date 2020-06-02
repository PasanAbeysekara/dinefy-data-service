package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/30/2020 9:01 PM
 */

@Data
@Entity
@Table(name = "promotion_tiers")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
//@JsonIdentityInfo(
//		generator = ObjectIdGenerators.PropertyGenerator.class,
//		property = "tierId")
public class PromotionTier
{
	@Id
	@Column(name = "tier_id")
	private Short tierId;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Size(max = 2000)
	@Column(name = "description")
	private String description;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "tier", fetch = FetchType.LAZY)
	private Set<Promotion> promotions;


}
