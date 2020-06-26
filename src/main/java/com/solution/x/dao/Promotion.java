package com.solution.x.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.solution.x.dao.key.PromoID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * @author Tharinda Wickramaarachchi
 * @since 5/30/2020 9:01 PM
 */

@Data
@Entity
@Table(name = "promotions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "promoId")
public class Promotion extends RepresentationModel<Promotion>
{
	@EmbeddedId
	@EqualsAndHashCode.Include
	private PromoID promoId;

	@Size(max = 200)
	@Column(name = "name")
	private String name;

	@Size(max = 2000)
	@Column(name = "description")
	private String description;

	@Column(name = "tier_id")
	private Short tierId;

	@Column(name = "promo_type")
	private Short promoTypeId;

	@Column(name = "start_date")
	private Date start;

	@Column(name = "end_date")
	private Date end;

	@Column(name = "live")
	private Boolean live;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tier_id", insertable = false, updatable = false)
	private PromotionTier tier;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prop_id", insertable = false, updatable = false)
	@ToString.Exclude
	private Property properties;
}
