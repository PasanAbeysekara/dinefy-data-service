package com.solution.x.dao.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solution.x.dao.Property;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/23/2020 10:58 PM
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment_options")
@ToString
public class PaymentOptions extends RepresentationModel<PaymentOptions>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id", updatable = false, nullable = false)
	private Short optionId;

	@Size(max = 20)
	@Column(name = "name")
	private String name;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "prop_payment_options",
			inverseJoinColumns = @JoinColumn(name = "prop_id", referencedColumnName = "prop_id", insertable = false, updatable = false),
			joinColumns = @JoinColumn(name = "option_id", referencedColumnName = "option_id")
	)
	@ToString.Exclude
	private Set<Property> weekDefinitions;
}
