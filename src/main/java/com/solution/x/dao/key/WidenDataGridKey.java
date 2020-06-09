package com.solution.x.dao.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Tharinda Wickramaarachchi
 * @since 6/5/2020 9:36 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class WidenDataGridKey implements Serializable
{
	@NotNull
	@Column(name = "prop_id")
	private Long propId;

	@NotNull
	@Column(name = "avail_unit_id")
	private Integer availUnitId;

	@NotNull
	@Column(name = "date")
	private LocalDate date;

	@NotNull
	@Column(name = "time_slot")
	private LocalTime timeSlot;
}
