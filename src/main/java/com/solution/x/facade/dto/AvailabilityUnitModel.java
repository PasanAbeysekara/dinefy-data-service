package com.solution.x.facade.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

/**
 * @author Tharinda Wickramaarachchi
 * @since 4/20/2020 9:09 PM
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AvailabilityUnitModel extends RepresentationModel<AvailabilityUnitModel>
{
	@EqualsAndHashCode.Include
	private Integer unitId;

	@EqualsAndHashCode.Include
	private String code;

	private String name;
	private Short minCapacity;
	private Short maxCapacity;

}
