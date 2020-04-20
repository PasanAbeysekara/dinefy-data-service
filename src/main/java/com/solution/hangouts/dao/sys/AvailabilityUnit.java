package com.solution.hangouts.dao.sys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.solution.hangouts.dao.PropAvailabilityUnit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/20/2020 9:09 PM
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "sys_availability_unit")
public class AvailabilityUnit extends RepresentationModel<AvailabilityUnit>
{
	@Id
	@SequenceGenerator(
			name = "sys_avail_unit_seq",
			sequenceName = "sys_avail_unit_seq",
			initialValue = 100
	)
	@EqualsAndHashCode.Include
	private Integer unit_id;

	@NotBlank
	@Size(max = 10)
	@Column(name = "code")
	@EqualsAndHashCode.Include
	private String code;

	@Size(max = 100)
	@Column(name = "name")
	private String name;

	@Column(name = "min_capacity")
	private Short minCapacity;

	@Column(name = "max_capacity")
	private Short maxCapacity;

	@JsonBackReference
	@OneToMany(mappedBy = "sysAvailabilityUnit", fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Set<PropAvailabilityUnit> propAvailabilityUnits;

}
